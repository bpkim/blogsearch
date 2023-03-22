package com.blogsearch.lib.blog.service;

import com.blogsearch.client.BlogService;
import com.blogsearch.client.model.BlogApiResponse;
import com.blogsearch.lib.blog.model.dto.BlogDocumentResDto;
import com.blogsearch.lib.blog.model.dto.BlogMetaResDto;
import com.blogsearch.lib.blog.model.dto.BlogSearchDto;
import com.blogsearch.lib.blog.model.mapper.BlogDocumentResDtoMapper;
import com.blogsearch.lib.blog.model.mapper.BlogMetaResDtoMapper;
import com.blogsearch.lib.common.ApiResponse;
import com.blogsearch.lib.common.annotation.SearchLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BlogMainServiceImpl implements BlogMainService {

    private final static String [] BLOG_SERVICE_ARR = {"kakao", "naver"};

    private final BlogServiceFactory blogServiceFactory;
    private final BlogMetaResDtoMapper blogMetaResDtoMapper;
    private final BlogDocumentResDtoMapper blogDocumentResDtoMapper;

    public BlogMainServiceImpl(BlogServiceFactory blogServiceFactory
                                , BlogMetaResDtoMapper blogMetaResDtoMapper
                                , BlogDocumentResDtoMapper blogDocumentResDtoMapper) {
        this.blogServiceFactory = blogServiceFactory;
        this.blogMetaResDtoMapper = blogMetaResDtoMapper;
        this.blogDocumentResDtoMapper = blogDocumentResDtoMapper;
    }

    /**
     * 블로그 검색
     * @param reqDto
     * @return
     */
    @Override
    @SearchLog
    public ApiResponse<BlogMetaResDto, List<BlogDocumentResDto>> findBlog(BlogSearchDto reqDto) {
        ApiResponse<BlogMetaResDto, List<BlogDocumentResDto>> result = new ApiResponse<>();
        BlogApiResponse blogApiResponse = null;

        for(String blogServiceName : BLOG_SERVICE_ARR) {
            BlogService blogService = blogServiceFactory.getBlogService(blogServiceName);
            blogApiResponse = blogService.findBlog(reqDto.getQuery(), reqDto.getSort(), reqDto.getPage(), reqDto.getSize());

            // 결과값 있으면 다음 서비스에서 조회 안하고 for 문 종료
            if(blogApiResponse != null) {
                // 검색결과가 없으면 다른 블로그 서비스에서 검색
                if(blogApiResponse.getMeta().getTotalCount() == 0){
                    continue;
                }
                break;
            }
        }

        if(blogApiResponse == null || blogApiResponse.getMeta().getTotalCount() == 0){
            BlogMetaResDto metaResDto = BlogMetaResDto.builder()
                    .isEnd(true)
                    .pageableCount(0L)
                    .totalCount(0L)
                    .service("")
                    .build();
//            BlogDocumentResDto blogDocumentResDto = BlogDocumentResDto.builder().build();

            result.setMeta(metaResDto);
            result.setDocuments(new ArrayList<>());
            return result;
        }

        BlogMetaResDto blogMetaResDto = blogMetaResDtoMapper.blogMetaDtoToBlogMetaResDto(blogApiResponse.getMeta());
        List<BlogDocumentResDto> blogDocumentResDtos =blogDocumentResDtoMapper.blogDocumentDtosToBlogDocumentResDtos(blogApiResponse.getDocuments());
        result.setMeta(blogMetaResDto);
        result.setDocuments(blogDocumentResDtos);

        return result;
    }

}
