package com.base.project.domain.user.service;


import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import com.base.project.global.config.SecurityConfig.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     *
     * @param userAccount
     * @return userId
     */
    @Transactional
    public Long signup(UserAccount userAccount) {
        userRepository.save(userAccount);
        return userAccount.getId();
    }

    public void validateDuplicateNickname(String nickname) {
        List<UserAccount> userAccounts = userRepository.findByNickname(nickname);
        if (!userAccounts.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_Nickname);
        }
    }

    public void validateDuplicateEmail(String email) {
        List<UserAccount> userAccounts = userRepository.findByEmail(email);
        if (!userAccounts.isEmpty()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    //유저 전체 조회
    public List<UserAccount> findUsers() {
        return userRepository.findAll();
    }

    //유저 id로 단건 조회
    public UserAccount findOne(Long userId) {
        Optional<UserAccount> userAccount = userRepository.findById(userId);
        if (userAccount.isPresent()) {
            return userAccount.get();
        } else
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }
}