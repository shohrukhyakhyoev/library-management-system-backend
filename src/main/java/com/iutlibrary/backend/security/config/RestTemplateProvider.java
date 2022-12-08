package com.iutlibrary.backend.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Provides a REST TEMPLATE object.
 */
@Configuration
public class RestTemplateProvider {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
