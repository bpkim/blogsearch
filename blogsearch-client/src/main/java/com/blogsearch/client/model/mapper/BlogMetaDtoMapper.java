package com.blogsearch.client.model.mapper;

import com.blogsearch.client.model.BlogMetaDto;
import com.blogsearch.client.naver.model.NaverBlogApiResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BlogMetaDtoMapper {
    BlogMetaDtoMapper INSTANCE = Mappers.getMapper(BlogMetaDtoMapper.class);


    @Mapping(source = "total", target = "totalCount")
    BlogMetaDto _NaverBlogMetaDtoToBlogMetaDto(NaverBlogApiResponse naverBlogApiResponse);

    default BlogMetaDto NaverBlogMetaDtoToBlogMetaDto(NaverBlogApiResponse naverBlogApiResponse) {
        BlogMetaDto blogResMetaDto = _NaverBlogMetaDtoToBlogMetaDto(naverBlogApiResponse);

        blogResMetaDto.setService("naver");
        blogResMetaDto.setPageableCount(0L);

        // 마지막인지 여부
        blogResMetaDto.setIsEnd(false);
        if(naverBlogApiResponse.getTotal() < naverBlogApiResponse.getStart() + naverBlogApiResponse.getDisplay()) {
           blogResMetaDto.setIsEnd(true);
        }
        return blogResMetaDto;
    }

}
