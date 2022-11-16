package com.mustache.bbs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs.domain.dto.ArticleAddRequest;
import com.mustache.bbs.domain.dto.ArticleAddResponse;
import com.mustache.bbs.domain.dto.ArticleResponseDTO;
import com.mustache.bbs.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

    @Test
    @DisplayName("한 개의 게시글 등록하기")
    void addArticle() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목입니다.", "내용입니다.");
        given(articleService.addArticle(any())).willReturn(new ArticleAddResponse(3L, dto.getTitle(), dto.getContents()));

        Long articleId = 3L;
        String url = String.format("/api/v1/articles/%d", articleId);

        mockMvc.perform(
                        post(url)
                                .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목입니다.", "내용입니다.")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("제목입니다."))
                .andExpect(jsonPath("$.contents").exists())
                .andDo(print());

        verify(articleService).addArticle(refEq(dto));
    }
}