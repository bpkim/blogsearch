package com.blogsearch.client.kakao.service;

import com.blogsearch.client.BlogService;
import com.blogsearch.client.kakao.client.KakaoBlogFeignClient;
import com.blogsearch.client.model.BlogApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KakaoBlogServiceImpl implements BlogService {

    private final KakaoBlogFeignClient kakaoBlogFeignClient;

    public KakaoBlogServiceImpl(KakaoBlogFeignClient kakaoBlogFeignClient) {
        this.kakaoBlogFeignClient = kakaoBlogFeignClient;
    }

    /**
     *
     * 네이버블로그 조회
     * @param query
     * @param sort
     * @param page
     * @param size
     * @return
     */
    @Override
    public BlogApiResponse findBlog(String query, String sort, Integer page, Integer size) {
        BlogApiResponse result = null;

        try {
            result = kakaoBlogFeignClient.searchBlog(query, sort, page, size);
        } catch(Exception e){
            log.error("Exception kakaoBlogFeignClient.searchBlog msg : {} "+e.getMessage());
            result = null;
        } finally {

        }

        if(result == null) {
            return null;
        }

        return result;
    }
}
