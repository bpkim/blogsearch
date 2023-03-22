package com.blogsearch.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogDocumentDto {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;
}
