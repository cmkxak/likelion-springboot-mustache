package com.mustache.bbs.controller;

import com.mustache.bbs.domain.dto.UserResponseDTO;
import com.mustache.bbs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("api/v1/users")
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getuser(@PathVariable Long id){
        UserResponseDTO userResponseDTO = userService.findById(id);
        return ResponseEntity.ok().body(userResponseDTO);
    }
}
