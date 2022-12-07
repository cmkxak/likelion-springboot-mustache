package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.user.UserJoinResponse;
import com.mustache.bbs.domain.dto.user.UserJoinRequest;
import com.mustache.bbs.domain.dto.user.UserLoginRequest;
import com.mustache.bbs.domain.dto.user.UserLoginResponse;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.exception.ErrorCode;
import com.mustache.bbs.exception.UserNotFoundException;
import com.mustache.bbs.repository.UserRepository;
import com.mustache.bbs.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private final long expireTimeMs = 100 * 60 * 60;

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        userRepository.findByUserName(userJoinRequest.getUserName()).ifPresent(user -> {
            throw new UserNotFoundException(ErrorCode.DUPLICATE_USER.getHttpStatus(), "유저가 이미 존재합니다.");
        });

        String rawPassword = userJoinRequest.getPassword();
        User savedUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(rawPassword)));

        return UserJoinResponse.builder()
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .build();
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        //유저네임이랑 패스워드 입력받아서, 디비의 유저와 일치하는지
        User findUser = userRepository.findByUserName(userLoginRequest.getUserName()).orElseThrow(() ->
                new UserNotFoundException(ErrorCode.NOT_FOUND_USER.getHttpStatus(), "유저가 존재하지 않습니다."));

        if (!encoder.matches(userLoginRequest.getPassword(), findUser.getPassword())) {
            throw new UserNotFoundException(ErrorCode.INVALID_ID_PASSWORD.getHttpStatus(), "비밀번호가 잘못되었습니다.");
        }

        String token = JwtTokenUtil.createToken(userLoginRequest.getUserName(), secretKey, expireTimeMs);
        return UserLoginResponse.builder()
                .token(token)
                .build();
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() ->
                new UserNotFoundException(ErrorCode.NOT_FOUND_USER.getHttpStatus(), "유저가 존재하지 않습니다"));
    }
}
