package com.jsp.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.Product.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
