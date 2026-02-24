package com.ai.lessaon1.controller;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.lessaon1.service.AiService;

@RestController
@RequestMapping
public class ChatController {

    @SuppressWarnings("unused")
    private final ChatClient geminiChatClient;
    private final ChatClient ollamChatClient;
    private final AiService aiService;

    
    
    public ChatController(@Qualifier("geminiChatClient") ChatClient geminiChatClient, @Qualifier("ollamChatClient") ChatClient ollamChatClient, AiService aiService) {
        this.geminiChatClient = geminiChatClient;
        this.ollamChatClient = ollamChatClient;
        this.aiService = aiService;
    }



    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String query){
        String resContent = this.ollamChatClient.prompt(query).call().content();

        return ResponseEntity.ok(resContent);
    }

    @GetMapping("/dynamic-chat")
    public ResponseEntity<String> dynamicModalGenerate(@RequestParam(value = "q") String query,
            @RequestParam(defaultValue = "fast") String type){
                Map<String, String> map = Map.of("fast","llama3.1:latest","code","deepseek-coder:latest");
                
        String resContent = aiService.generate(map.get(type),query);

        return ResponseEntity.ok(resContent);
    }
}
