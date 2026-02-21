package com.ai.lessaon1.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(@Qualifier("ollamChatClient")ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generate(String modelName, String prompt) {

        OllamaChatOptions options = OllamaChatOptions.builder()
                .model(modelName)
                .temperature(0.7)
                .build();

        return chatClient
                .prompt(prompt)
                .options(options)
                .call()
                .content();
    }
}
