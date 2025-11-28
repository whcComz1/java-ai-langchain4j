package com.atguigu.javaailangchain4j;


import com.atguigu.javaailangchain4j.assistant.MemoryChatAssistant;
import com.atguigu.javaailangchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testSystemMessage() {
        String answer = separateChatAssistant.chat(3,"今天几月几号星期几");
        System.out.println(answer);
    }
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testUserMessage() {
        String answer1 = memoryChatAssistant.chat("我是whcc");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("你好");
        System.out.println(answer2);
        String answer3 = memoryChatAssistant.chat("今天星期几");
        System.out.println(answer3);
    }

    @Test
    public void testV() {
        String answer1 = separateChatAssistant.chat2(1, "我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat2(1, "我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserInfo() {
        String username = "小王";
        int age = 20;
        String answer = separateChatAssistant.chat3(20, "我是谁，我多大了", username, age);
        System.out.println(answer);
    }

}
