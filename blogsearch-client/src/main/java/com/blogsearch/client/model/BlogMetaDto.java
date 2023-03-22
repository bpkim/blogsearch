package com.blogsearch.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogMetaDto {
    @JsonProperty("total_count")
    private Long totalCount;
    @JsonProperty("pageable_count")
    private Long pageableCount;
    @JsonProperty("is_end")
    private Boolean isEnd;
    private String service = "kakao";
}
