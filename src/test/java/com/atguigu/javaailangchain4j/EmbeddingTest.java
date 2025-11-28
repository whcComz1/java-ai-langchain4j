package com.atguigu.javaailangchain4j;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class EmbeddingTest {

    @Autowired
    private EmbeddingModel embeddingModel;
    @Test
    public void testEmbeddingModel() {
        Response<Embedding> embed = embeddingModel.embed("你好");
        System.out.println("向量维度：" + embed.content().vector().length);
        System.out.println("向量输出：" + embed.toString());
    }

    /**
     * 使用的是redis向量数据库
     */

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    /**
     * redis向量数据库测试
     */
    @Test
    public void test1() {
        // 将指定的数据向量化，并存入redis
        TextSegment segment1 = TextSegment.from("我爱踢足球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);

        TextSegment segment2 = TextSegment.from("今天天气正好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);

        // 向需要比对的内容向量化
        Embedding queryEmbedding = embeddingModel.embed("你最喜欢的运动是什么？").content();
        // 创建搜索对象
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(6) // 指定返回的搜索结果的最大个数
                .build();
        // 进行相似度搜索
        List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingSearchRequest).matches();
        // 获取匹配的数据
        EmbeddingMatch<TextSegment> embeddingMatch = matches.get(0);
        // 打印计算的结果
        System.out.println(embeddingMatch.score());
        System.out.println(embeddingMatch.embedded().text());
    }
    @Test
    public void testUploadKnowledgeLibrary() {
        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
        //并使用默认的文档解析器对文档进行解析
        Document document1 = FileSystemDocumentLoader.loadDocument("D:\\BaiduNetdiskDownload\\Xiaozhi资料\\knowledge\\knowledge\\医院信息.md");
        Document document2 = FileSystemDocumentLoader.loadDocument("D:\\BaiduNetdiskDownload\\Xiaozhi资料\\knowledge\\knowledge\\科室信息.md");
        Document document3 = FileSystemDocumentLoader.loadDocument("D:\\BaiduNetdiskDownload\\Xiaozhi资料\\knowledge\\knowledge\\神经内科.md");
        List<Document> documents = Arrays.asList(document1, document2, document3);

    //文本向量化并存入向量数据库：将每个片段进行向量化，得到一个嵌入向量
        EmbeddingStoreIngestor
            .builder()
            .embeddingStore(embeddingStore)
            .embeddingModel(embeddingModel)
            .build()
            .ingest(documents);
    }

}
