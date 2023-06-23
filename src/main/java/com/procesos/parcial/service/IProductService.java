package com.procesos.parcial.service;

import com.procesos.parcial.dto.ProductRequestDto;
import com.procesos.parcial.model.Product;

import java.util.List;

/**
 *  Interface for loose coupling of services.
 */
public interface IProductService {

    /**
     * Method to create product by client.
     * @param product Instance of Product class.
     */
    void createProduct(ProductRequestDto product);

    /**
     * Method to create (import) all products from the API.
     */
    void importAllProducts();

    /**
     * Method to create (import) a product by ID from the API.
     * @param id Product identifier.
     * @return The product found in the API.
     */
    Product createProductById(Long id);

    /**
     * Method to obtain a Product by ID.
     * @param id Product identifier.
     * @return The product found.
     */
    Product getProductById(Long id);

    /**
     * Method to obtain all Products.
     * @return List of Products found.
     */
    List<Product> getAllProducts();

    /**
     * Method to update a product by ID.
     * @param id Product identifier.
     * @param product Product data.
     */
    void updateProduct(Long id, ProductRequestDto product);

}
