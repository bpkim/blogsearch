package com.blogsearch.api.blog;

import com.blogsearch.api.blog.model.BlogApiReqValidator;
import com.blogsearch.api.blog.model.dto.BlogApiReqDto;
import com.blogsearch.api.blog.model.mapper.BlogApiReqDtoMapper;
import com.blogsearch.api.common.ErrorsResources;
import com.blogsearch.lib.blog.model.dto.BlogDocumentResDto;
import com.blogsearch.lib.blog.model.dto.BlogMetaResDto;
import com.blogsearch.lib.blog.model.dto.BlogSearchDto;
import com.blogsearch.lib.blog.service.BlogMainService;
import com.blogsearch.lib.common.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/api/blog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogApiController {

    private final BlogMainService blogMainService;
    private final BlogApiReqValidator blogReqValidator;
    private final BlogApiReqDtoMapper blogSearchReqDtoMapper;

    public BlogApiController(BlogMainService blogMainService, BlogApiReqValidator blogReqValidator, BlogApiReqDtoMapper blogSearchReqDtoMapper) {
        this.blogMainService = blogMainService;
        this.blogReqValidator = blogReqValidator;
        this.blogSearchReqDtoMapper = blogSearchReqDtoMapper;
    }

    /**
     * 블로그 조회 API
     * @param reqDto
     * @param errors
     * @return
     */
    @GetMapping
    public ResponseEntity queryBlog(@Valid BlogApiReqDto reqDto, Errors errors) {

        if (errors.hasErrors()) {
            return badRequest(errors);
        }

        blogReqValidator.validate(reqDto, errors);

        if (errors.hasErrors()) {
            return badRequest(errors);
        }

        BlogSearchDto blogSearchDto = blogSearchReqDtoMapper.blogReqDtoToBlogSearchDto(reqDto);
        ApiResponse<BlogMetaResDto, List<BlogDocumentResDto>> blogResMetaDtoListApiResponse = blogMainService.findBlog(blogSearchDto);

        return ResponseEntity.ok(blogResMetaDtoListApiResponse);

    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(ErrorsResources.modelOf(errors));
    }
}
