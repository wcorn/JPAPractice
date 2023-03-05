package com.base.project.domain.user.entity;

import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MemberHistory extends BaseEntity {


    private String name;

    private String nickname;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}