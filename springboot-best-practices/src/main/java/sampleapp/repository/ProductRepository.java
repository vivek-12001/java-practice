package sampleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sampleapp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
