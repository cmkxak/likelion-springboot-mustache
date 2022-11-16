package com.mustache.bbs.domain.dto;

import jdk.jshell.Snippet;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String contents;
}
