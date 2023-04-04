package com.procesos.parcial.service;

import com.procesos.parcial.model.Product;

import java.util.List;

public interface IProductService {

    void createProduct(Product product);

    void importAllProducts();

    Product createProductById(Long id);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

}
