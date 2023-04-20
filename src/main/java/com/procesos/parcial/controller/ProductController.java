package com.procesos.parcial.controller;

import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.messages.MessageProduct;
import com.procesos.parcial.model.Product;
import com.procesos.parcial.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product controller class.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    /**
     * Product Service interface injection.
     */
    private final IProductService productService;

    /**
     * Constant message
     */
    private static final String MESSAGE = "message";

    /**
     * Method post to import all products from the API.
     * @return ResponseEntity with the status and message.
     */
    @PostMapping("/import")
    public ResponseEntity<Map<String, String>> importAllProducts() {
        productService.importAllProducts();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, MessageProduct.PRODUCT_IMPORT.getMessage()));
    }

    /**
     * Method post to import a product from the API.
     * @param id Product identifier.
     * @return ResponseEntity with the status, message and product data.
     */
    @PostMapping("/{id}")
    public ResponseEntity<Map<String, Object>> createProductById(@PathVariable Long id) {
        Product product = productService.createProductById(id);

        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE, MessageProduct.PRODUCT_CREATED.getMessage());
        response.put("data", product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Method post to create a product by the customer.
     * @param product Instance of Product class.
     * @return ResponseEntity with the status and message.
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody Product product) {
        productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, MessageProduct.PRODUCT_CREATED.getMessage()));
    }

    /**
     * Method get to obtain all products.
     * @return ResponseEntity with the status, message and list products.
     */
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        if (productList == null || productList.isEmpty()) {
            throw new NoDataFoundException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE, MessageProduct.PRODUCT_LIST.getMessage());
        response.put("data", productList);

        return ResponseEntity.ok(response);
    }

    /**
     * Method get to obtain a Product by id.
     * @param id Product identifier.
     * @return ResponseEntity with the status, message and product data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new NoDataFoundException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE, MessageProduct.PRODUCT_FIND.getMessage());
        response.put("data", product);

        return ResponseEntity.ok(response);
    }

    /**
     * Method put to update a Product by id with the customer's data.
     * @param id Product identifier.
     * @param product Instance Product class.
     * @return ResponseEntity with the status and message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        if (product == null) {
            throw new NoDataFoundException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(MESSAGE, MessageProduct.PRODUCT_UPDATED.getMessage());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
