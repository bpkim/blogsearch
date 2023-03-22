package com.blogsearch.client.naver.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverBlogDocumentDto {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;
}
