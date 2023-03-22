package com.blogsearch.api.blog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("블로그 검색 API 테스트")
    public void searchBlog() throws Exception {


        mockMvc.perform(get("/api/blog")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("query", "검색")
                        .param("page", "1")
                        .param("size", "10")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("meta").exists())
                .andExpect(jsonPath("meta.totalCount").exists())
                .andExpect(jsonPath("meta.pageableCount").exists())
                .andExpect(jsonPath("meta.isEnd").exists())
                .andExpect(jsonPath("documents").exists())
        ;

    }
}