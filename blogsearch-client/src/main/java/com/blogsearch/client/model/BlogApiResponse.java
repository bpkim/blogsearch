package com.blogsearch.client.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class BlogApiResponse {
    private BlogMetaDto meta;
    private List<BlogDocumentDto> documents;
}
