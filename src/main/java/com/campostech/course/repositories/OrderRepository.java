package com.campostech.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campostech.course.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
