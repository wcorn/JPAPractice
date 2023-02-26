package com.base.project.domain.order.entity;

import com.base.project.domain.delivery.entity.DeliveryStatus;
import com.base.project.domain.orderItem.entity.OrderItem;
import com.base.project.domain.delivery.entity.Delivery;
import com.base.project.domain.user.entity.UserAccount;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.entity.BaseEntity;
import com.base.project.global.common.exception.CustomException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@ToString
@Table(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
    @OneToMany(mappedBy = "order", cascade = ALL)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 메서드==//
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //==생성메서드==//
    public static Order createOrder(UserAccount userAccount, Delivery delivery, OrderItem... orderItems) {

        Order order = Order.builder()
                .userAccount(userAccount)
                .delivery(delivery)
                .status(OrderStatus.ORDER)
                .build();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new CustomException(ErrorCode.ALREADY_DELIVERY);
        }
        this.status = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    /**
     * 전체 주문가격 조회
     *
     * @return
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
