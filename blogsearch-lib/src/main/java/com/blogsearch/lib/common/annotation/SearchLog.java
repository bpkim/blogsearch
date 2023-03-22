package com.blogsearch.lib.common.annotation;

import java.lang.annotation.*;

/**
 * 검색어 기록을 위한 어노테이션
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface SearchLog {
}
