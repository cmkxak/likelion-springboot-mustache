package com.mustache.bbs.domain.dto.user;

import com.mustache.bbs.domain.entity.User;
import lombok.*;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password) {
        return User.builder()
                .userName(this.userName)
                .password(password)
                .email(this.email)
                .build();
    }
}
