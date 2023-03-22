package com.blogsearch.client.naver.client.failback;

import com.blogsearch.client.naver.client.NaverBlogFeignClient;
import com.blogsearch.client.naver.model.NaverBlogApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NaverBlogFailbackFactory implements FallbackFactory<NaverBlogFeignClient> {
    @Override
    public NaverBlogFeignClient create(Throwable cause) {

        return new NaverBlogFeignClient() {

            @Override
            public NaverBlogApiResponse searchBlog(String query, Integer display, Integer start, String sort) {
                log.error("NaverBlogFeignClient.searchBlog() : query {}, display {}, start {}, sort {}", query, display, start, sort);
                return null;
            }
        };
    }
}
