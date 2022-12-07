package com.mustache.bbs.domain.dto.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String contents;
}
