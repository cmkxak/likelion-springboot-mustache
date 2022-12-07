package com.mustache.bbs.controller;

import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("/join")
    public String join(){
        return "users/join";
    }

    @GetMapping("/login")
    public String login(){
        return "/users/login";
    }

}
