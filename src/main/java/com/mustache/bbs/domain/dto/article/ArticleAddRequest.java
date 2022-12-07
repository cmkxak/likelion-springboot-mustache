package com.mustache.bbs.domain.dto.article;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAddRequest {
    private String title;
    private String contents;

    public Article toArticle(){
        return new Article(title, contents);
    }
}
