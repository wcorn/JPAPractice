package com.base.project.domain.user.service;

import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.repository.UserRepository;
import com.base.project.global.common.exception.CustomException;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
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
    void signup() throws Exception {
        UserAccount userAccount = UserAccount.builder()
                .name("kim")
                .build();
        Long userId = userService.join(userAccount);
        assertEquals(userAccount,userRepository.findById(userId).get());
    }
    @Test
    void validationEmail() throws Exception{
        UserAccount userAccount1 = UserAccount.builder()
                .email("dsk08208@gmail.com")
                .build();
        UserAccount userAccount2 = UserAccount.builder()
                .email("dsk08208@gmail.com")
                .build();
        userService.join(userAccount1);
        Assertions.assertThrows(CustomException.class, () -> {
            userService.validateDuplicateEmail(userAccount2);
        });
    }
    @Test
    void validationNickname() throws Exception{
        UserAccount userAccount1 = UserAccount.builder()
                .nickname("peter")
                .build();
        UserAccount userAccount2 = UserAccount.builder()
                .nickname("peter")
                .build();
        userService.join(userAccount1);
        Assertions.assertThrows(CustomException.class, () -> {
            userService.validateDuplicateNickname(userAccount2);
        });
    }
}