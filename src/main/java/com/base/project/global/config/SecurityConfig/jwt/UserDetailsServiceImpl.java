package com.base.project.global.config.SecurityConfig.jwt;


import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserAccount> userAccount = userRepository.findById(Long.parseLong(username));
        return User.withUsername(username)
                .password(userAccount.get().getName())
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .build();
    }
}