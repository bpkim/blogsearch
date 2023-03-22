package com.blogsearch.lib.blog.service;

import com.blogsearch.client.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class BlogServiceFactoryImpl implements BlogServiceFactory {

    private final List<BlogService> blogService;

    public BlogServiceFactoryImpl(List<BlogService> blogService) {
        this.blogService = blogService;
    }

    /**
     * 서비스 명으로 BlogService Bean 얻기
     * @param service
     * @return
     */
    @Override
    public BlogService getBlogService(String service) {
        return blogService.stream().filter(x-> x.getClass().getSimpleName().toLowerCase(Locale.ROOT).indexOf(service) > -1).findFirst().get();
    }
}
