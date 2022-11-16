package com.mustache.bbs.domain.entity;

import com.mustache.bbs.domain.dto.ArticleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    public static ArticleResponseDTO of(Long id, String title, String contents){
        return new ArticleResponseDTO(id, title, contents);
    }
}
