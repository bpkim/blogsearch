package com.blogsearch.lib.common;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<M,T>{
    private M meta;
    private T documents;
}
