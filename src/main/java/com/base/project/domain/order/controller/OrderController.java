package com.base.project.domain.order.controller;

import com.base.project.domain.order.dto.OrderSearch;
import com.base.project.domain.order.entity.Order;
import com.base.project.domain.order.entity.OrderStatus;
import com.base.project.domain.order.service.OrderService;

import com.base.project.global.common.api.ApiResponse;
import com.base.project.global.common.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Order>>> getOrders(@RequestParam(required = false) String username, @RequestParam(required = false) OrderStatus orderstatus, Pageable pageable) {
        try {
            OrderSearch orderSearch = OrderSearch.builder()
                    .userName(username)
                    .orderStatus(orderstatus)
                    .build();
            List<Order> orders = orderService.findPagingByUserNameAndOrderStatus(orderSearch, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),pageable.getSort()));
            log.info(pageable.getSort()+" "+pageable.getPageNumber()+" "+pageable.getPageSize());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.orderList,orders));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(ResponseCode.TEST));
        }
    }
}
