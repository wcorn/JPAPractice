package com.base.project.domain.order.controller;

import com.base.project.domain.Item.service.ItemService;
import com.base.project.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class OrderController {

    private final OrderService orderService;

}
