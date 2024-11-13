package com.programming.techie.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
@Configuration
public class Webclientconfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webclientBuilder() {
        return WebClient.builder();
    }
}