package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.dto.ArticleResponseDTO;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }

    public Article save(Article article){
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public ArticleResponseDTO getArticle(Long id){
        Article article = articleRepository.findById(id).orElse(null);
        ArticleResponseDTO articleResponseDTO = Article.of(article.getId(), article.getTitle(), article.getContents());
        return articleResponseDTO;
    }
}
