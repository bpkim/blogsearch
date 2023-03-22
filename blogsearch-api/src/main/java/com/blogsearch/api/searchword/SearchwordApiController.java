package com.blogsearch.api.searchword;

import com.blogsearch.lib.common.ApiResponse;
import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResDocumentDto;
import com.blogsearch.lib.searchword.model.dto.QuerySearchWordRankResMedaDto;
import com.blogsearch.lib.searchword.service.SearchwordService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping(value ="/api/blog/searchword", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchwordApiController {

    private final SearchwordService searchService;

    public SearchwordApiController(SearchwordService searchService) {
        this.searchService = searchService;
    }

    /**
     * 검색어 순위 조회 API
     * @param rank
     * @return
     */
    @GetMapping(value = {"/rank/{rank}", "/rank"})
    public ResponseEntity searchwordRankList(@PathVariable(name = "rank", required = false)
                                         @Min(value = 1, message = "rank has to be more than or equal to 1")
                                         @Max(value = 10, message = "rank has to be less than or equal to 10") Integer rank) {

        // rank 순위 없으면 10이 기본
        if(rank == null) {
            rank = 10;
        }

        ApiResponse<QuerySearchWordRankResMedaDto, List<QuerySearchWordRankResDocumentDto>> apiResponse = searchService.findSearchwordRank(rank);

        return ResponseEntity.ok(apiResponse);
    }



}
