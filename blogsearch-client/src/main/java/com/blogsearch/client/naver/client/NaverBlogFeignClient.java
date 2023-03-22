package com.blogsearch.client.naver.client;

import com.blogsearch.client.naver.model.NaverBlogApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverFeignClient", url = "${service.naver.url}", configuration = NaverBlogFeignClientConfig.class)
public interface NaverBlogFeignClient {

    @GetMapping("/search/blog.json")
    NaverBlogApiResponse searchBlog(@RequestParam(value = "query") String query
                                , @RequestParam(value = "display") Integer display
                                , @RequestParam(value = "start") Integer start
                                , @RequestParam(value = "sort") String sort
                                );
}
