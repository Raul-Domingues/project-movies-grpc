package com.example.filmeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient omdbWebClient() {
        return WebClient.builder()
                .baseUrl("http://www.omdbapi.com")
                .build();
    }
}
