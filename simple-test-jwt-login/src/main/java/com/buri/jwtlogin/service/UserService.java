package com.buri.jwtlogin.service;

import com.buri.jwtlogin.model.dto.request.UserDto;
import com.buri.jwtlogin.model.entity.Authority;
import com.buri.jwtlogin.model.entity.User;
import com.buri.jwtlogin.repository.UserRepository;
import com.buri.jwtlogin.config.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(UserDto userDto) {
        // username이 DB에 존재하지 않으면 Authority와 User 정보를 생성해서 UserRepository의 sava 메소드를 통해 DB에 정보를 저장
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 권한정보 생성 >> 빌더 패턴의 장점
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        // 유저정보 생성
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> getUserWithAuthorities(String username) {
        // SecurityContext에 저장된 username의 정보만 가져옴.
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional
    public Optional<User> getCurUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
