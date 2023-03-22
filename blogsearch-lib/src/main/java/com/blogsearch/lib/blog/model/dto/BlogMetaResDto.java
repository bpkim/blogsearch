package com.blogsearch.lib.blog.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogMetaResDto {

    public BlogMetaResDto() {
    }

    public BlogMetaResDto(String service, Long totalCount, Long pageableCount, Boolean isEnd) {
        this.service = service;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }

    private String service;
    private Long totalCount;
    private Long pageableCount;
    private Boolean isEnd;
}
