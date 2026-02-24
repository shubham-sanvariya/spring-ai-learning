package com.ai.lesson2_advisors.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ai.lesson2_advisors.TokenPrintAdvisor;

@Configuration
public class AiConfig {
    

    @Bean
    ChatClient chatClient(ChatClient.Builder builder){
        return builder
        .defaultAdvisors(new TokenPrintAdvisor(),new SafeGuardAdvisor(List.of("games")))
        .defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
        .defaultOptions(
            OllamaChatOptions.builder()
            .model("deepseek-coder:latest")
            .temperature(0.7)
            .numPredict(1000)
            .build()
        )
        .build();
    }
}
