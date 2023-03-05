package com.base.project.domain.user.entity;

import com.base.project.domain.review.entity.Review;
import com.base.project.domain.user.listener.MemberEntityListener;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {MemberEntityListener.class})
public class Member extends BaseEntity {

    private String name;

    private String nickname;

    private String email;

    private String password;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false)
    @Builder.Default
    @ToString.Exclude
    private List<MemberHistory> memberHistories = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}