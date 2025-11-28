package com.atguigu.javaailangchain4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

@SpringBootApplication
//        (exclude = {
//                dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration.class
//        })
public class JavaAiLangchain4jApplication {

    public static void main(String[] args) {

        SpringApplication.run(JavaAiLangchain4jApplication.class, args);
    }

}
