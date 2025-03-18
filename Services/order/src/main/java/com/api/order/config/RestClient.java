package com.api.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClient {

        @Bean
        public RestClient restClient() {
            return new RestClient();
        }
    }
