package com.base.project.global.config.SecurityConfig.jwt;

import com.base.project.domain.user.dto.response.AccessTokenDto;
import com.base.project.domain.user.repository.UserRepository;
import com.base.project.global.common.api.ApiResponse;
import com.base.project.global.common.api.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String username = authentication.getName();
        String accessToken = jwtTokenProvider.createAccessToken(username);
        AccessTokenDto tokenDto = new AccessTokenDto(accessToken);
        tokenDto.setToken(accessToken);
        ApiResponse<AccessTokenDto> result = new ApiResponse<>(ResponseCode.USER_LOGIN, tokenDto);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), result);
    }

}