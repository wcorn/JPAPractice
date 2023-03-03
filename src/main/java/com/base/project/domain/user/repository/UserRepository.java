package com.base.project.domain.user.repository;

import com.base.project.domain.user.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

}
