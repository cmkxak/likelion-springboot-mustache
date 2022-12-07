package com.mustache.bbs.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddResponse {
    private Long id;
    private String title;
    private String contents;
}
