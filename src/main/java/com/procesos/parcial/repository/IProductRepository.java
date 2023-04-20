package com.procesos.parcial.repository;

import com.procesos.parcial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product repository interface
 */
public interface IProductRepository extends JpaRepository<Product, Long> {

    /**
     * Method to find a Product by ID.
     * @param id Product identifier.
     * @return The product found.
     */
    Product findProductById(Long id);
}
