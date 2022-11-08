package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.ArticleDto;
import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@RequestMapping("/articles")
@AllArgsConstructor
@Slf4j
@Controller
public class ArticleController {
    private ArticleService articleService;

    @GetMapping("")
    public String index(){
        return "redirect:/articles/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/new")
    public String articles(){
        return "articles/new";
    }

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleService.findById(id);

        if(optArticle.isPresent()){
            Article article = optArticle.get();
            model.addAttribute("article", article);
            return "articles/show";
        }else{
            return "articles/error";
        }
    }

    @PostMapping("/posts")
    public String createArticle(ArticleDto articleDto){
        log.info(articleDto.toString());
        Article article = articleDto.toEntity();
        articleService.save(article);
        return "";
    }
}
