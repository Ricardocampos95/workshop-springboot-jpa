package com.campostech.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campostech.course.entities.OrderItem;
import com.campostech.course.entities.pk.OrderItemPK;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
