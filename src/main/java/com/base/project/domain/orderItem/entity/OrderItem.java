package com.base.project.domain.orderItem.entity;


import com.base.project.domain.Item.entity.Item;
import com.base.project.domain.order.entity.Order;
import com.base.project.global.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class OrderItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="Item_id")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "Order_id")
    private Order order;
    private int orderPrice;
    private int count;
}
