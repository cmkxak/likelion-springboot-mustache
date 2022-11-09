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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        Article savedArticle = articleDto.toEntity();
        articleService.save(savedArticle);
        return "redirect:/articles/" + savedArticle.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Article article = articleService.findById(id).orElse(null);
        //모델에 데이터 등록
        model.addAttribute("article", article);
        //뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/{id}/update")
    public String edit(@PathVariable Long id, ArticleDto articleDto, Model model){
        Article article = articleDto.toEntity();
        log.info(article.toString());

        Article savedArticle = articleService.save(article);
        model.addAttribute("article", savedArticle);
        return "redirect:/articles/" + savedArticle.getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article article = articleService.findById(id).orElse(null);
        log.info(article.toString());

        if(article != null){
            articleService.delete(article);
            rttr.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
        }

        return "redirect:/articles";
    }
}
