package com.example.crudapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudapplication.entity.Product;
import com.example.crudapplication.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return service.createProduct(product);
	}
	
	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> product) {
		return service.createProducts(product);
	}
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return service.getProducts();
	}

	@Cacheable(value = "products", key = "#id")
	@GetMapping("/productById/{id}")
	public Optional<Product> getProductById(@PathVariable int id) {
		System.out.println("inside getProductByid");
		return service.getProductById(id);
	}

	@CacheEvict(value = "products", key = "#id")
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProductById(id);
	}

	@CachePut(value = "products", key = "#id")
	@PutMapping("/update/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable int id) {
		System.out.println("inside update method");
		return service.updateProduct(product);
	}
}
