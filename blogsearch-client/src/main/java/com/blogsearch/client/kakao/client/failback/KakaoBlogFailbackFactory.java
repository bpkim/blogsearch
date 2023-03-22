package com.blogsearch.client.kakao.client.failback;

import com.blogsearch.client.kakao.client.KakaoBlogFeignClient;
import com.blogsearch.client.model.BlogApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KakaoBlogFailbackFactory implements FallbackFactory<KakaoBlogFeignClient> {
    @Override
    public KakaoBlogFeignClient create(Throwable cause) {

        return new KakaoBlogFeignClient() {
            @Override
            public BlogApiResponse searchBlog(String query, String sort, Integer page, Integer size) {
                log.error("kakaoBlogFeignClient.searchBlog() : query {}, sort {}, page {}, size {}", query, sort, page, size);
                return null;
            }
        };
    }
}
