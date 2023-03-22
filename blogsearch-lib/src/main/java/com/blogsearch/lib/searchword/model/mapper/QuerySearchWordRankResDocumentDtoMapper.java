package com.blogsearch.lib.searchword.model.mapper;

import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResDocumentDto;
import com.blogsearch.lib.searchword.model.entity.SearchwordRank;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface QuerySearchWordRankResDocumentDtoMapper {
    QuerySearchWordRankResDocumentDtoMapper INSTANCE = Mappers.getMapper(QuerySearchWordRankResDocumentDtoMapper.class);

    QuerySearchWordRankResDocumentDto SearchwordRankToQuerySearchWordRankResDocumentDto(SearchwordRank searchwordRank);
    List<QuerySearchWordRankResDocumentDto> SearchwordRanksToQuerySearchWordRankResDocumentDtos(List<SearchwordRank> searchwordRank);

}
