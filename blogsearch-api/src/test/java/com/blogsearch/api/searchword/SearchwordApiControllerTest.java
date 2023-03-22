package com.blogsearch.api.searchword;

import com.blogsearch.lib.searchword.model.entity.Searchword;
import com.blogsearch.lib.searchword.model.entity.SearchwordLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SearchwordApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("블로그 검색어 랭킹 10 API 테스트")
    public void searchwordRank10() throws Exception {

        String [] arrWord = {"word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8", "word9", "word10", "word11"};

        for(int i = 0 ; i < arrWord.length ; i ++) {
            for(int j = 0 ; j < i ; j ++){
                String tmpWord = arrWord[i];
                Searchword searchworda = new Searchword(tmpWord);
                em.persist(searchworda);
                SearchwordLog searchwordaLog = new SearchwordLog(searchworda);
                searchwordaLog.setRegDt(LocalDateTime.now());
                em.persist(searchwordaLog);
            }

        }

        mockMvc.perform(get("/api/blog/searchword/rank")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("meta").exists())
                .andExpect(jsonPath("meta.totalCount").exists())
                .andExpect(jsonPath("meta.searchwordCount").exists())
                .andExpect(jsonPath("documents[0].rank").exists())
                .andExpect(jsonPath("documents[0].searchword").exists())
                .andExpect(jsonPath("documents[0].count").exists())
                // 10번째 순위까지 확인
                .andExpect(jsonPath("documents[9].rank").exists())
                .andExpect(jsonPath("documents[9].searchword").exists())
                .andExpect(jsonPath("documents[9].count").exists());



        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("블로그 검색어 랭킹 3 API 테스트")
    public void searchwordRank3() throws Exception {

        String [] arrWord = {"word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8", "word9", "word10"};

        for(int i = 0 ; i < arrWord.length ; i ++) {
            for(int j = 0 ; j < i ; j ++){
                String tmpWord = arrWord[i];
                Searchword searchworda = new Searchword(tmpWord);
                em.persist(searchworda);
                SearchwordLog searchwordaLog = new SearchwordLog(searchworda);
                searchwordaLog.setRegDt(LocalDateTime.now());
                em.persist(searchwordaLog);
            }

        }

        mockMvc.perform(get("/api/blog/searchword/rank/3")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("meta").exists())
                .andExpect(jsonPath("meta.totalCount").exists())
                .andExpect(jsonPath("meta.searchwordCount").exists())
                .andExpect(jsonPath("documents[0].rank").exists())
                .andExpect(jsonPath("documents[0].searchword").exists())
                .andExpect(jsonPath("documents[0].count").exists())
                // 4번째 부터 없음 확인
                .andExpect(jsonPath("documents[4].rank").doesNotExist())
                .andExpect(jsonPath("documents[4].searchword").doesNotExist())
                .andExpect(jsonPath("documents[4].count").doesNotExist());



        em.flush();
        em.clear();
    }
}