package com.procesos.parcial.repository;

import com.procesos.parcial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);
}
