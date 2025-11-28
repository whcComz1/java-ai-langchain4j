package com.atguigu.javaailangchain4j;

import com.atguigu.javaailangchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


@SpringBootTest
public class MongoCrudTest {
    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
//    public void  testInsert() {
//        mongoTemplate.insert(new ChatMessages(1L, "hello world"));
//    }


    // 插入文档
    @Test
    public void testInsert2() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages);
    }
    // 查找
    @Test
    public void testFindById() {
        ChatMessages chatMessages = mongoTemplate.findById("6922c003aa69942f9e72a2f7",
                ChatMessages.class);
        System.out.println(chatMessages);
    }

    @Test
    public void testUpdate() {
        Criteria criteria = Criteria.where("_id").is("6922c003aa69942f9e72a2f7");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "更新后的聊天记录列表");
        // 修改或新增
        mongoTemplate.upsert(query, update, ChatMessages.class);
    }
    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id").is("6922c003aa69942f9e72a2f7");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
}
