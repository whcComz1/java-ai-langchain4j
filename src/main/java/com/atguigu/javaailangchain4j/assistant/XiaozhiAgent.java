package com.atguigu.javaailangchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
        wiringMode = EXPLICIT,
        //chatModel = "qwenChatModel",
        streamingChatModel = "qwenStreamingChatModel",  // 配置文件中配置了就可以直接这样写
        chatMemoryProvider = "chatMemoryProviderXiaozhi",
        tools = "appointmentTools",                    //tools配置
        contentRetriever = "contentRetrieverXiaozhi")  //配置向量存储
public interface XiaozhiAgent {
    @SystemMessage(fromResource = "zhaozhi-prompt-template.txt")
    // String chat(@MemoryId Long memoryId, @UserMessage String userMessage);

    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
