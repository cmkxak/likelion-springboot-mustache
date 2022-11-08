package com.mustache.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MustacheController {

    @GetMapping("/hi/{id}")
    public String mustacheCon(Model model, @PathVariable Long id){
        model.addAttribute("username", "cmkxak");
        model.addAttribute("userId", id);
        return "greetings";
    }
}
