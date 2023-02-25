package com.base.project.domain.delivery.entity;

import com.base.project.domain.order.entity.Order;
import com.base.project.domain.user.entity.Address;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString
@EqualsAndHashCode(callSuper = true)
public class Delivery extends BaseEntity {
    @OneToOne(mappedBy = "delivery", fetch = LAZY, cascade = ALL)
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void setOrder(Order order) {
        this.order = order;
    }
}