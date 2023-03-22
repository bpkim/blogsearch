package com.blogsearch.api.blog.model.mapper;

import com.blogsearch.api.blog.model.dto.BlogApiReqDto;
import com.blogsearch.lib.blog.model.dto.BlogSearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BlogApiReqDtoMapper {
    BlogApiReqDtoMapper INSTANCE = Mappers.getMapper(BlogApiReqDtoMapper.class);

    BlogSearchDto blogReqDtoToBlogSearchDto(BlogApiReqDto blogReqDto);


}
