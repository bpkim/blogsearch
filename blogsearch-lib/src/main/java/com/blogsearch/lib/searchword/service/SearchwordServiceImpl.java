package com.blogsearch.lib.searchword.service;

import com.blogsearch.lib.common.ApiResponse;
import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResDocumentDto;
import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResMedaDto;
import com.blogsearch.lib.searchword.model.entity.Searchword;
import com.blogsearch.lib.searchword.model.entity.SearchwordLog;
import com.blogsearch.lib.searchword.model.entity.SearchwordRank;
import com.blogsearch.lib.searchword.model.mapper.QuerySearchWordRankResDocumentDtoMapper;
import com.blogsearch.lib.searchword.repository.SearchwordLogRepository;
import com.blogsearch.lib.searchword.repository.SearchwordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class SearchwordServiceImpl implements SearchwordService {

    private final SearchwordRepository searchRepository;
    private final SearchwordLogRepository searchLogRepository;
    private final QuerySearchWordRankResDocumentDtoMapper querySearchWordRankResDocumentDtoMapper;

    public SearchwordServiceImpl(SearchwordRepository searchRepository
                                , SearchwordLogRepository searchLogRepository
                                , QuerySearchWordRankResDocumentDtoMapper querySearchWordRankResDocumentDtoMapper) {
        this.searchRepository = searchRepository;
        this.searchLogRepository = searchLogRepository;
        this.querySearchWordRankResDocumentDtoMapper = querySearchWordRankResDocumentDtoMapper;
    }

    /**
     * 검색어 저장
     * @param searchword
     */
    @Override
    public Searchword saveSearchword(String searchword) {

        Searchword getSearchword = searchRepository.findBySearchword(searchword);

        if(getSearchword == null){
            Searchword newsearch = new Searchword();
            newsearch.setSearchword(searchword);
            getSearchword = searchRepository.save(newsearch);
        }
        SearchwordLog searchwordLog = new SearchwordLog();
        searchwordLog.setSearchword(getSearchword);
        searchwordLog.setRegDt(LocalDateTime.now());
        SearchwordLog saveKeyowrdLog = searchLogRepository.save(searchwordLog);

        return getSearchword;
    }

    /**
     * 검색어 순위 조회 및 순위 정보 조회
     * @param rank
     * @return
     */
    @Override
    public ApiResponse<QuerySearchWordRankResMedaDto, List<QuerySearchWordRankResDocumentDto>> findSearchwordRank(Integer rank) {
        List<SearchwordRank> searchRanks = this.selectSearchwordRank(rank);
        Long searchwordCount = this.findSearchwordCount();
        Long searchwordLogCount = this.findSearchwordLogCount();

        List<QuerySearchWordRankResDocumentDto> querySearchWordRankResDocumentDtos = querySearchWordRankResDocumentDtoMapper.SearchwordRanksToQuerySearchWordRankResDocumentDtos(searchRanks);

        QuerySearchWordRankResMedaDto resMedaDto = new QuerySearchWordRankResMedaDto();
        resMedaDto.setTotalCount(searchwordLogCount);
        resMedaDto.setSearchwordCount(searchwordCount);

        ApiResponse<QuerySearchWordRankResMedaDto, List<QuerySearchWordRankResDocumentDto>> apiResponse = new ApiResponse<>(resMedaDto, querySearchWordRankResDocumentDtos);
        return apiResponse;

    }

    /**
     * 검색어 순위 조회
     * @param rank
     * @return
     */
    private List<SearchwordRank> selectSearchwordRank(int rank) {
        return searchRepository.findSearchwordRank(rank);
    }

    /**
     * 총 검색어 수 조회
     * @return
     */
    private Long findSearchwordCount() {
        return searchRepository.count();
    }

    /**
     * 총 검색 횟수 조회
     * @return
     */
    private Long findSearchwordLogCount() {
        return searchLogRepository.count();
    }
}
