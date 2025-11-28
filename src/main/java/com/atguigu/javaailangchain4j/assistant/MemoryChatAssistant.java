package com.atguigu.javaailangchain4j.assistant;


import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;


// 初级智能体
@AiService(wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory") // chatMemory在配置文件中
public interface MemoryChatAssistant {
    @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。{{message}}") //{{it}}表示这里唯一的参数的占位符，未来替换的就是message
    String chat(@V("message")String message);
}
