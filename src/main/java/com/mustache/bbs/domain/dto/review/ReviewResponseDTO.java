package com.mustache.bbs.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {
    private String title;
    private String contents;
    private String username;
 }
