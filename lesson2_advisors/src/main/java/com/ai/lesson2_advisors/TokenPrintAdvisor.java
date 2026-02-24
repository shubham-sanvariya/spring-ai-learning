package com.ai.lesson2_advisors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;

import reactor.core.publisher.Flux;

public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor{

    private Logger logger = LoggerFactory.getLogger(TokenPrintAdvisor.class);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        Flux<ChatClientResponse> chatClientResponseFlux = streamAdvisorChain.nextStream(chatClientRequest);

        return chatClientResponseFlux;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        logger.info("My Token print advisor is called:");

        logger.info("Request: " + chatClientRequest.prompt().getContents());

        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);

        logger.info("Token Advisor: Response received from the model:");

        logger.info("Response: " + chatClientResponse.chatResponse().getResult().getOutput().getText());

        logger.info("Prompt Token: " + chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        logger.info("Completion Token: " + chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        logger.info("Total Token consumed: " + chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());
        
        return chatClientResponse;
    }

}
