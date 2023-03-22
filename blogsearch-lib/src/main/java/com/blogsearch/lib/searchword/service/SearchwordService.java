package com.blogsearch.lib.searchword.service;

import com.blogsearch.lib.common.ApiResponse;
import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResDocumentDto;
import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResMedaDto;
import com.blogsearch.lib.searchword.model.entity.Searchword;

import java.util.List;

public interface SearchwordService {

    Searchword saveSearchword(String searchword);

    ApiResponse<QuerySearchWordRankResMedaDto, List<QuerySearchWordRankResDocumentDto>> findSearchwordRank(Integer rank);

}
