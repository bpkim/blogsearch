package com.blogsearch.lib.searchword.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuerySearchWordRankResDocumentDto {
    private Integer rank;
    private String searchword;
    private Long count;
    private String minDatetime;
    private String maxDatetime;
}
