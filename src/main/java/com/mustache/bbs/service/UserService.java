package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.UserDTO;
import com.mustache.bbs.domain.dto.UserJoinRequest;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.UserNotFoundException;
import com.mustache.bbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserDTO join(UserJoinRequest userJoinRequest) {
        userRepository.findByUserName(userJoinRequest.getUserName()).ifPresent(user -> {
                    throw new UserNotFoundException(ErrorCode.DUPLICATE_USER.getHttpStatus(), "유저가 이미 존재합니다.");
                });

        String rawPassword = userJoinRequest.getPassword();
        User savedUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(rawPassword)));

        return UserDTO.builder()
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .build();
    }
}
