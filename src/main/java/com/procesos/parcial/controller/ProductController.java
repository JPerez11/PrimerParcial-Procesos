package com.procesos.parcial.controller;

import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.messages.MessageProduct;
import com.procesos.parcial.model.Product;
import com.procesos.parcial.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService productService;
    private final RestTemplate restTemplate;
    private static final String MESSAGE = "message";

    @PostMapping("/import")
    public ResponseEntity<Map<String, String>> importAllProducts() {
        String url = "https://fakestoreapi.com/products/";
        Product[] product = restTemplate.getForObject(url, Product[].class);
        List<Product> productList = null;
        if (product != null) {
            productList = Arrays.asList(product);
        }
        productService.importAllProducts(productList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(MESSAGE, MessageProduct.PRODUCT_IMPORT.getMessage()));
    }

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



}
