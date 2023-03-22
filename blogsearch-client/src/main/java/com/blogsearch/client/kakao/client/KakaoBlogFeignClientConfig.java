package com.blogsearch.client.kakao.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoBlogFeignClientConfig {

    @Value("${service.kakao.appkey}")
    String appKey;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json;charset=UTF-8");
            requestTemplate.header("Authorization", "KakaoAK "+appKey);
        };
    }
}
