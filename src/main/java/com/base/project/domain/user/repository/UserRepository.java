package com.base.project.domain.user.repository;

import com.base.project.domain.user.entity.UserAccount;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    List<UserAccount> findByEmail(String email);

    List<UserAccount> findByNickname(String nickname);

}
