package com.procesos.parcial.service.impl;

import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.exception.ProductAlreadyExistsException;
import com.procesos.parcial.model.Product;
import com.procesos.parcial.repository.IProductRepository;
import com.procesos.parcial.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Product service class that implements the methods of the interface.
 * Transactional annotation for requests to the database.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    /**
     * Repository Constructor Injection.
     */
    private final IProductRepository productRepository;

    /**
     * RestTemplate Constructor Injection
     */
    private final RestTemplate restTemplate;

    @Override
    public void createProduct(Product product) {
        // Product is saved
        productRepository.save(product);
    }

    @Override
    public void importAllProducts() {
        // The URL is stored in a String
        String url = "https://fakestoreapi.com/products/";
        // A list of the Products is obtained by the RestTemplate query
        Product[] product = restTemplate.getForObject(url, Product[].class);
        // Verify that null is not coming
        assert product != null;
        // The list is converted to an ArrayList
        List<Product> productList =  Arrays.asList(product);
        for (Product prod :
                productList) {
            if (productRepository.existsById(prod.getId())) {
                throw new ProductAlreadyExistsException();
            }
        }
        // Products are saved.
        productRepository.saveAll(productList);
    }

    @Override
    public Product createProductById(Long id) {
        // The URL plus the id is stored in a String
        String url = "https://fakestoreapi.com/products/"+id;
        // The Product is obtained by the RestTemplate query
        Product product = restTemplate.getForObject(url, Product.class);
        // Verify that null is not coming
        assert product != null;
        if (productRepository.existsById(product.getId())) {
            throw new ProductAlreadyExistsException();
        }
        // Product is saved
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        // The product is searched
        Product product = productRepository.findProductById(id);
        // Verify that null is not coming
        if (product == null) {
            // If the product is not found it throws an exception
            throw new NoDataFoundException();
        }
        // The Product is returned
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        // All Products are searched
        List<Product> productList = productRepository.findAll();
        // Verify that the list is not empty
        if (productList.isEmpty()) {
            // exception is thrown
            throw new NoDataFoundException();
        }
        // All Products are returned
        return productList;
    }

    @Override
    public void updateProduct(Long id, Product product) {
        // The product is searched
        Product productToUpdate = productRepository.findProductById(id);
        // Verify that null is not coming
        if (productToUpdate == null) {
            // Exception is thrown
            throw new NoDataFoundException();
        }

        // The old data is replaced with the customer data
        productToUpdate.setTitle(product.getTitle() );
        productToUpdate.setPrice( product.getPrice() );
        productToUpdate.setDescription( product.getDescription() );
        productToUpdate.setCategory(product.getCategory() );
        productToUpdate.setImage(product.getImage() );

    }
}
