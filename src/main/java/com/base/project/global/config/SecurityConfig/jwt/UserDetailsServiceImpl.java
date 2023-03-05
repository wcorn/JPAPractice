package com.base.project.global.config.SecurityConfig.jwt;


import com.base.project.domain.user.entity.Member;
import com.base.project.domain.user.repository.MemberRepository;
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

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Member> userAccount = memberRepository.findById(Long.parseLong(username));
        return User.withUsername(username)
                .password(userAccount.get().getPassword())
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .build();
    }
}