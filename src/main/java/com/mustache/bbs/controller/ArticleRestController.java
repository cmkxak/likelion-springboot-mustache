package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.article.ArticleAddRequest;
import com.mustache.bbs.domain.dto.article.ArticleAddResponse;
import com.mustache.bbs.domain.dto.article.ArticleResponseDTO;
import com.mustache.bbs.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}")
    public ResponseEntity<ArticleAddResponse> add(@RequestBody ArticleAddRequest articleAddRequest){
        ArticleAddResponse articleAddResponse = articleService.addArticle(articleAddRequest);

        //200 ok if articleAddResponse exists
        return ResponseEntity.ok().body(articleAddResponse);
    }
}
