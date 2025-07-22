package com.campostech.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campostech.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
