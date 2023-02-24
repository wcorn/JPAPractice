package com.base.project.domain.user.entity;

import com.base.project.domain.order.entity.Order;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString
public class UserAccount extends BaseEntity {

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "userAccount")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
