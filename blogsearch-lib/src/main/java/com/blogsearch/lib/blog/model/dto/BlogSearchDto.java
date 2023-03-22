package com.blogsearch.lib.blog.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogSearchDto {

    private String query;
    private String  sort;
    private Integer page;
    private Integer size;
}
