package com.blogsearch.client.naver.service;

import com.blogsearch.client.BlogService;
import com.blogsearch.client.model.BlogApiResponse;
import com.blogsearch.client.model.BlogDocumentDto;
import com.blogsearch.client.model.BlogMetaDto;
import com.blogsearch.client.model.mapper.BlogMetaDtoMapper;
import com.blogsearch.client.naver.client.NaverBlogFeignClient;
import com.blogsearch.client.naver.model.NaverBlogApiResponse;
import com.blogsearch.client.model.mapper.BlogDocumentDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NaverBlogServiceImpl implements BlogService {

    private final NaverBlogFeignClient naverBlogFeignClient;
    private final BlogMetaDtoMapper naverBlogMetaDtoMapper;
    private final BlogDocumentDtoMapper naverBlogDocumentDtoMapper;

    public NaverBlogServiceImpl(NaverBlogFeignClient naverBlogFeignClient, BlogMetaDtoMapper naverBlogMetaDtoMapper, BlogDocumentDtoMapper naverBlogDocumentDtoMapper) {
        this.naverBlogFeignClient = naverBlogFeignClient;
        this.naverBlogMetaDtoMapper = naverBlogMetaDtoMapper;
        this.naverBlogDocumentDtoMapper = naverBlogDocumentDtoMapper;
    }

    /**
     * 네이버 블로그 조회
     * @param query
     * @param sort
     * @param page
     * @param size
     * @return
     */
    @Override
    public BlogApiResponse findBlog(String query, String sort, Integer page, Integer size) {

        NaverBlogApiResponse naverBlogApiResponse = null;

        String naverSort = "sim";
        Integer display = size;

        Integer start = 1;
        if(display == null) {
            display = 10;
        }
        if(page == null || page == 0) {
            start = 1;
        } else {
            start = (page-1) * display + 1;
        }
        if (start > 100) {
            start = 100;
        }
        try {

            naverBlogApiResponse = naverBlogFeignClient.searchBlog(query, display, start, naverSort);

        } catch(Exception e) {
            log.error("Exception kakaoBlogFeignClient.searchBlog msg : {} "+e.getMessage());
        }


        BlogMetaDto blogMetaDto = naverBlogMetaDtoMapper.NaverBlogMetaDtoToBlogMetaDto(naverBlogApiResponse);
        List<BlogDocumentDto> blogDocumentDtos = naverBlogDocumentDtoMapper.NaverBlogDocumentDtosToBlogDocumentDtos(naverBlogApiResponse.getItems());

        return BlogApiResponse.builder()
                .meta(blogMetaDto)
                .documents(blogDocumentDtos).build();
    }
}
