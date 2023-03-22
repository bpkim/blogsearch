package com.blogsearch.client.naver.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverBlogFeignClientConfig {

    @Value("${service.naver.client-id}")
    String clientId;

    @Value("${service.naver.client-secret}")
    String clientSecret;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", clientId);
            requestTemplate.header("X-Naver-Client-Secret", clientSecret);
        };
    }
}
