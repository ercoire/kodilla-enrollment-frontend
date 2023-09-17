package com.example.kodillaenrollmentfrontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${backend.url}")
    private String backendBaseUrl;

    @Bean
    public WebClient createWebClient(){
        return WebClient.create(backendBaseUrl);
    }
}
