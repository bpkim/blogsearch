package com.blogsearch.lib.searchword.model.entity;

public interface SearchwordRank {

    Integer getRank();
    String getSearchword();
    Long getCount();
    String getMinDatetime();
    String getMaxDatetime();
}
