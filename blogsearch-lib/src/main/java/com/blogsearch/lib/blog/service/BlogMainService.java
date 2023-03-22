package com.blogsearch.lib.blog.service;

import com.blogsearch.lib.blog.model.dto.BlogDocumentResDto;
import com.blogsearch.lib.blog.model.dto.BlogMetaResDto;
import com.blogsearch.lib.blog.model.dto.BlogSearchDto;
import com.blogsearch.lib.common.ApiResponse;

import java.util.List;

public interface BlogMainService {
    ApiResponse<BlogMetaResDto, List<BlogDocumentResDto>> findBlog(BlogSearchDto reqDto);
}
