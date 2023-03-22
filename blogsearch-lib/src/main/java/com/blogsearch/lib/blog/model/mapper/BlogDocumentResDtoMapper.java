package com.blogsearch.lib.blog.model.mapper;

import com.blogsearch.client.model.BlogDocumentDto;
import com.blogsearch.client.naver.model.dto.NaverBlogDocumentDto;
import com.blogsearch.lib.blog.model.dto.BlogDocumentResDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.LinkedList;
import java.util.List;


@Mapper(componentModel = "spring")
public interface BlogDocumentResDtoMapper {
    BlogDocumentResDtoMapper INSTANCE = Mappers.getMapper(BlogDocumentResDtoMapper.class);

    BlogDocumentResDto blogDocumentDtoToBlogDocumentResDto(BlogDocumentDto blogDocumentDto);

    default List<BlogDocumentResDto> blogDocumentDtosToBlogDocumentResDtos(List<BlogDocumentDto> blogDocumentDtos) {
        List<BlogDocumentResDto> resDocumentDtos = new LinkedList<>();
        for(BlogDocumentDto documentDto : blogDocumentDtos) {
            resDocumentDtos.add(blogDocumentDtoToBlogDocumentResDto(documentDto));
        }
        return resDocumentDtos;
    }
}
