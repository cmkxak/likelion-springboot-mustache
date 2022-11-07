package com.mustache.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MustacheController {

    @GetMapping("/hi")
    public String mustacheCon(Model model){
        model.addAttribute("username", "cmkxak");
        return "greetings";
    }
}
