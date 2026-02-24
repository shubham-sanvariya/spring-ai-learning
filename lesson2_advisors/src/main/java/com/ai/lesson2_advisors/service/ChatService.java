package com.ai.lesson2_advisors.service;

import reactor.core.publisher.Flux;

public interface ChatService {
    
    String chatTemplate(String query);

    Flux<String> streamchat(String query);
}
