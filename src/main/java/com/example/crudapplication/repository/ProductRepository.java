package com.example.crudapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudapplication.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

}
