package com.base.project.domain.user.controller;

import com.base.project.domain.user.dto.request.SignUpRequestDto;
import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.service.UserService;

import com.base.project.global.common.api.ApiResponse;
import com.base.project.global.common.api.ResponseCode;
import com.base.project.global.utils.Regex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody SignUpRequestDto signUpRequestDto) {

        Regex.isRegexEmail(signUpRequestDto.getEmail());
        userService.validateDuplicateEmail(signUpRequestDto.getEmail());
        userService.validateDuplicateNickname(signUpRequestDto.getNickname());
        UserAccount userAccount = UserAccount.builder()
                .name(signUpRequestDto.getName())
                .nickname(signUpRequestDto.getNickname())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();
        userService.signup(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ResponseCode.USER_SIGNUP));
    }
}