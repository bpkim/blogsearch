package com.blogsearch.api.common;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse{
    private String errorType;
    private String message;
}
