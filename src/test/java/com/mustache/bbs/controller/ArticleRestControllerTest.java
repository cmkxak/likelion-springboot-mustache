package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleResponseDTO;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("한 개의 게시글 조회 하기")
    void getArticle() throws Exception {
        ArticleResponseDTO articleResponseDTO = ArticleResponseDTO.builder()
                .id(1L)
                .title("gg")
                .contents("gg")
                .build();

        given(articleService.getArticle(1L)).willReturn(articleResponseDTO);

        Long articleId = 1L;

        String url = String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").exists())
                .andDo(print());

        verify(articleService).findById(articleId);
    }
}