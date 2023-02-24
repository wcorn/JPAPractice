package com.base.project.domain.user.service;


import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import com.base.project.global.config.SecurityConfig.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 회원가입
     * @param userAccount
     * @return userId
     */
    @Transactional
    public Long join(UserAccount userAccount){
        validateDuplicateEmail(userAccount); //중복 메일 검증
        validateDuplicateNickname(userAccount); //중복 닉네임 검증
        userRepository.save(userAccount);
        return userAccount.getId();
    }

    private void validateDuplicateNickname(UserAccount userAccount) {
        List<UserAccount> userAccounts = userRepository.findByNickname(userAccount.getNickname());
        if(!userAccounts.isEmpty()){
            throw new CustomException(ErrorCode.DUPLICATED_Nickname);
        }
    }

    private void validateDuplicateEmail(UserAccount userAccount) {
        List<UserAccount> userAccounts = userRepository.findByEmail(userAccount.getEmail());
        if(!userAccounts.isEmpty()){
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }
    //유저 전체 조회
    public List<UserAccount> findUsers(){
        return userRepository.findAll();
    }
    //유저 id로 단건 조회
    public Optional<UserAccount> findOne(Long userId){
        return userRepository.findById(userId);
    }
}
