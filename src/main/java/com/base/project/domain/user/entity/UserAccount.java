package com.base.project.domain.user.entity;

import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserAccount extends BaseEntity {

    private String name;

    private String nickname;

    private String email;

    private String password;





}
