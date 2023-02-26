package com.base.project.domain.order.service;

import com.base.project.domain.Item.entity.Book;
import com.base.project.domain.Item.entity.Item;
import com.base.project.domain.Item.service.ItemService;
import com.base.project.domain.order.entity.Order;
import com.base.project.domain.order.entity.OrderStatus;
import com.base.project.domain.user.entity.Address;
import com.base.project.domain.user.entity.UserAccount;
import com.base.project.domain.user.service.UserService;
import com.base.project.global.common.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;

    @Test
    void order() {
        UserAccount userAccount = createUser();
        Book book = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(userAccount.getId(), book.getId(), orderCount);
        Order order = orderService.findOrder(orderId);
        assertEquals(OrderStatus.ORDER, order.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, order.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 하다");
        assertEquals(10000 * orderCount, order.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(10 - orderCount, book.getStockQuantity(), "주문 수량 만큰 재고가 줄어야 한다.");
    }

    @Test
    public void overTheInventory() throws Exception {
        UserAccount userAccount = createUser();
        Book item = createBook("시골 jpa", 10000, 10);
        int orderCount = 11;
        Assertions.assertThrows(CustomException.class, () -> {
            orderService.order(userAccount.getId(), item.getId(), orderCount);
        });
    }

    @Test
    void cancelOrder() {
        UserAccount userAccount = createUser();
        Book item = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(userAccount.getId(),item.getId(),orderCount);

        orderService.cancelOrder(orderId);

        Order order = orderService.findOrder(orderId);
        assertEquals(OrderStatus.CANCEL,order.getStatus(),"주문 취소시 상태는 CANCEL 이다.");
        assertEquals(10,item.getStockQuantity(),"주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");

    }

    @Test
    void findOrder() {
    }

    private UserAccount createUser() {
        UserAccount userAccount1 = UserAccount.builder()
                .name("회원1")
                .address(new Address("서울", "강가", "123-123"))
                .build();
        userService.signup(userAccount1);
        return userAccount1;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
        itemService.saveItem(book);
        return book;
    }
}