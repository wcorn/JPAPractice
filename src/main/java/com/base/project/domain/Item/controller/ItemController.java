package com.base.project.domain.Item.controller;

import com.base.project.domain.Item.service.ItemService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

}
