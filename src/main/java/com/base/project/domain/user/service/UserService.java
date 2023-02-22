package com.base.project.domain.user.service;

import com.base.project.domain.user.dto.request.LogInRequstDto;
import com.base.project.domain.user.dto.request.SignUpRequestDto;
import com.base.project.domain.user.dto.response.LogInResponseDto;
import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import com.base.project.global.config.SecurityConfig.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

            private final UserRepository userRepository;
            private final PasswordEncoder passwordEncoder;
            private final JwtTokenProvider jwtTokenProvider;


}
