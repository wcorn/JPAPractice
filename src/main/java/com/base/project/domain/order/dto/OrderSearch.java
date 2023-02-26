package com.base.project.domain.order.dto;


import com.base.project.domain.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderSearch {
    private String userName;
    private OrderStatus orderStatus;
}
