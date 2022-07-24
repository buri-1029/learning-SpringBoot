package com.buri.jwtlogin.controller;

import com.buri.jwtlogin.model.dto.request.UserDto;
import com.buri.jwtlogin.model.entity.User;
import com.buri.jwtlogin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @PostMapping
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')") // 허용권한 정해주기
    public ResponseEntity<User> getCurUserInfo() {
        return ResponseEntity.ok(userService.getCurUserWithAuthorities().get());
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") // 허용권한 정해주기 
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}
