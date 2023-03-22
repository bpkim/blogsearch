package com.blogsearch.client.naver.model;

import com.blogsearch.client.naver.model.dto.NaverBlogDocumentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NaverBlogApiResponse {
    private String lastBuildDate;
    private Long total;
    private Long start;
    private Long display;
    private List<NaverBlogDocumentDto> items;
}
