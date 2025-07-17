package com.campostech.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campostech.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
