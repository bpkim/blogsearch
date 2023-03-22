package com.blogsearch.api.blog.model;

import com.blogsearch.api.blog.model.dto.BlogApiReqDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

@Component
public class BlogApiReqValidator {

    public void validate(BlogApiReqDto reqDto, Errors errors){

        if(StringUtils.isEmpty(reqDto.getQuery())) {
            errors.reject("query","필수입력값입니다.");
        }

        if(!StringUtils.isEmpty(reqDto.getSort())
            && !("accuracy".equals(reqDto.getSort()) ||
                "recency".equals(reqDto.getSort()))){
            errors.reject("sort","accuracy 또는 recency 만 입력 가능합니다.");
        }

        if(reqDto.getPage() != null
            && (reqDto.getPage() < 1 || reqDto.getPage() > 50)) {
            errors.reject("page","1이상 50미만의 값만 입력 가능합니다.");

        }

        if(reqDto.getSize() != null
                && (reqDto.getSize() < 1 || reqDto.getSize() > 50)) {
            errors.reject("size", "1이상 50미만의 값만 입력 가능합니다.");
        }
    }
}
