package com.mustache.bbs.service;

import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public void save(Article article){
        articleRepository.save(article);
    }
}
