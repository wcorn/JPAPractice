package com.base.project.domain.user.controller;

import com.base.project.domain.user.dto.request.LogInRequstDto;
import com.base.project.domain.user.dto.request.SignUpRequestDto;
import com.base.project.domain.user.dto.response.LogInResponseDto;
import com.base.project.domain.user.service.UserService;

import com.base.project.global.common.api.ApiResponse;
import com.base.project.global.common.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


}