package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/articles")
@Controller
@Slf4j
public class ArticleController {

    @GetMapping("/new")
    public String articles(){
        return "articles/new";
    }

    @PostMapping("/posts")
    public String createArticle(ArticleDto articleDto){
        log.info(articleDto.toString());
        return "";
    }
}
