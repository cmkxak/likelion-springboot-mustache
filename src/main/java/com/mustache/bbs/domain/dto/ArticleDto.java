package com.mustache.bbs.domain.dto;

import com.mustache.bbs.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleDto {
    private Long id;
    private String title;
    private String contents;

    public Article toEntity(){
        return new Article(this.id, this.title, this.contents);
    }
}
