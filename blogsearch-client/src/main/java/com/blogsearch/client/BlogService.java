package com.blogsearch.client;

import com.blogsearch.client.model.BlogApiResponse;

public interface BlogService {
    BlogApiResponse findBlog(String query, String sort, Integer page, Integer size);
}
