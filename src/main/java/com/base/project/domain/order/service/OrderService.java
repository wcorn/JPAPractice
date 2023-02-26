package com.base.project.domain.order.service;


import com.base.project.domain.Item.entity.Item;
import com.base.project.domain.Item.service.ItemService;
import com.base.project.domain.delivery.entity.Delivery;
import com.base.project.domain.order.dto.OrderSearch;
import com.base.project.domain.order.entity.Order;
import com.base.project.domain.order.repository.OrderRepository;
import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.service.UserService;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ItemService itemService;

    /**
     * 주문 생성
     *
     * @param userId
     * @param itemId
     * @param count
     * @return
     */

    /**
     * 주문 취소
     *
     * @param orderId
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = findOrder(orderId);
        order.cancel();
    }

    /**
     * 주문 조회
     *
     * @param orderId
     * @return
     */

    public Order findOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new CustomException(ErrorCode.ORDER_NOT_FOUND);
        }
    }

    public List<Order> findPagingByUserNameAndOrderStatus(OrderSearch orderSearch, Pageable pageable){
        return orderRepository.findPagingByUserNameAndOrderStatus(orderSearch, pageable);
    }
}
