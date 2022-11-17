package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.UserResponseDTO;
import com.mustache.bbs.domain.entity.User;
import com.mustache.bbs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO findById(Long id){
        User user = userRepository.findById(id).orElse(null);
        return new UserResponseDTO(user.getId(), user.getUsername());
    }

}
