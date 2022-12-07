package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.article.ArticleAddRequest;
import com.mustache.bbs.domain.dto.article.ArticleAddResponse;
import com.mustache.bbs.domain.dto.article.ArticleResponseDTO;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

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

    public ArticleAddResponse addArticle(ArticleAddRequest articleAddRequest) {
        //requestdto -> entity
        Article article = articleAddRequest.toArticle();

        //save entity
        Article savedArticle = articleRepository.save(article);

        //entity -> responsedto
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContents());
    }
}
