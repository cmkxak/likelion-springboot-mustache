package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.UserDTO;
import com.mustache.bbs.domain.dto.UserJoinRequest;
import com.mustache.bbs.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String join(){
        return "users/join";
    }

    @PostMapping("/join")
    public ResponseEntity<UserDTO> join(UserJoinRequest userJoinRequest){
        UserDTO userDto = userService.join(userJoinRequest);
        return ResponseEntity.ok().body(userDto);
    }
}
