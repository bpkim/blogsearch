package com.blogsearch.lib.blog.model.mapper;

import com.blogsearch.client.model.BlogMetaDto;
import com.blogsearch.lib.blog.model.dto.BlogMetaResDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BlogMetaResDtoMapper {
    BlogMetaResDtoMapper INSTANCE = Mappers.getMapper(BlogMetaResDtoMapper.class);

    BlogMetaResDto blogMetaDtoToBlogMetaResDto(BlogMetaDto blogMetaDto);

}
