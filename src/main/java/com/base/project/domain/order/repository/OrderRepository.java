package com.base.project.domain.order.repository;

import com.base.project.domain.Item.entity.Item;
import com.base.project.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}