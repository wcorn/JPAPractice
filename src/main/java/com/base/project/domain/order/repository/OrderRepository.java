package com.base.project.domain.order.repository;

import com.base.project.domain.order.dto.OrderSearch;
import com.base.project.domain.order.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "" +
            "select o " +
            "from Order o join o.userAccount u "+
            "where o.status = :#{#orderSearch.orderStatus} " +
            "and u.name like :#{#orderSearch.userName} "
    )
    List<Order> findPagingByUserNameAndOrderStatus(@Param("orderSearch")OrderSearch orderSearch, Pageable pageable);
}