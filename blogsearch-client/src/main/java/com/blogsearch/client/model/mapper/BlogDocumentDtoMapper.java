package com.blogsearch.client.model.mapper;

import com.blogsearch.client.model.BlogDocumentDto;
import com.blogsearch.client.naver.model.dto.NaverBlogDocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface BlogDocumentDtoMapper {
    BlogDocumentDtoMapper INSTANCE = Mappers.getMapper(BlogDocumentDtoMapper.class);


    @Mapping(source = "title", target = "title")
    @Mapping(source = "link", target = "url")
    @Mapping(source = "bloggername", target = "blogname")
    @Mapping(source = "description", target = "contents")
    @Mapping(source = "postdate", target = "datetime")
    BlogDocumentDto NaverBlogDocumentDtoToBlogDocumentDto(NaverBlogDocumentDto naverBlogResDocumentDto);

    default List<BlogDocumentDto> NaverBlogDocumentDtosToBlogDocumentDtos(List<NaverBlogDocumentDto>  naverBlogResDocumentDtos) {
        List<BlogDocumentDto> resDocumentDtos = new ArrayList<>();
        for(NaverBlogDocumentDto documentDto : naverBlogResDocumentDtos) {
            resDocumentDtos.add(NaverBlogDocumentDtoToBlogDocumentDto(documentDto));
        }
        return resDocumentDtos;
    }
}
