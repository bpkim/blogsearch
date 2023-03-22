package com.blogsearch.client.kakao.client;

import com.blogsearch.client.model.BlogApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoBlogFeignClient", url = "${service.kakao.url}", configuration = KakaoBlogFeignClientConfig.class)
public interface KakaoBlogFeignClient {

    @GetMapping("/search/blog")
    BlogApiResponse searchBlog(@RequestParam(value = "query") String query
                                    , @RequestParam(value = "sort") String sort
                                    , @RequestParam(value = "page") Integer page
                                    , @RequestParam(value = "size") Integer size
                                    );
}
