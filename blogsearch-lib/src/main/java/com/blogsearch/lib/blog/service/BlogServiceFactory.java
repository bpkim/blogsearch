package com.blogsearch.lib.blog.service;

import com.blogsearch.client.BlogService;

public interface BlogServiceFactory {
    BlogService getBlogService(String service);
}
