package com.blogsearch.api.blog.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class BlogApiReqDto {

    @NotEmpty(message = "query parameter required")
    private String query;
    private String  sort;
    private Integer page;
    private Integer size;
}
