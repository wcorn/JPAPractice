package com.base.project.domain.orderItem.entity;


import com.base.project.domain.Item.entity.Item;
import com.base.project.domain.order.entity.Order;
import com.base.project.global.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Order_id")
    private Order order;
    private int orderPrice;
    private int count;


    //==생성 메소드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//

    /**
     * 취소되었으니 상태 원복
     */
    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}