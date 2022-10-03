package com.example.crudapplication.service;

import java.util.List;
import java.util.Optional;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudapplication.entity.Product;
import com.example.crudapplication.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Product createProduct(Product product) {
		return repository.save(product);
	}
	
	public List<Product> createProducts(List<Product> products) {
		return repository.saveAll(products);
	}
	
	public Optional<Product> getProductById(int id) {
		return repository.findById(id);
	}
	
	public List<Product> getProducts() {
		return repository.findAll();
	}
	
	public Product getProductByName(String name) {
		return repository.findByName(name);
	}
	
	public String deleteProductById(int id) {

		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
		repository.deleteById(id);
		return "Product deleted";
	}
	
	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		
		return repository.save(existingProduct);
	}
	
}
