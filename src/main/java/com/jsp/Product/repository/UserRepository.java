package com.jsp.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Product.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
