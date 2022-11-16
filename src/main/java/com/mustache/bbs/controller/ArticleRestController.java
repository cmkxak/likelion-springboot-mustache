package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleResponseDTO;
import com.mustache.bbs.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/articles")
@AllArgsConstructor
@RestController
public class ArticleRestController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> get(@PathVariable Long id){
        ArticleResponseDTO articleResponseDTO = articleService.getArticle(id);
        return ResponseEntity.ok().body(articleResponseDTO);
    }
}
