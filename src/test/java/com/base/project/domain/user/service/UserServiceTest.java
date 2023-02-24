package com.base.project.domain.user.service;

import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void 회원가입() throws Exception {
        UserAccount userAccount = UserAccount.builder()
                .name("kim")
                .build();
        Long userId = userService.join(userAccount);
        assertEquals(userAccount,userRepository.findById(userId).get());
    }

}