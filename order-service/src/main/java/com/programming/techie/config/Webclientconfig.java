package com.programming.techie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class Webclientconfig {

    @Bean
    public WebClient webclient() {
        return WebClient.builder().build();
    }
}