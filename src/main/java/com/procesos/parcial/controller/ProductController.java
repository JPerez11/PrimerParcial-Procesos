package com.procesos.parcial.controller;

import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.messages.MessageProduct;
import com.procesos.parcial.model.Product;
import com.procesos.parcial.service.IProductService;
import com.procesos.parcial.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Product controller class.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@SecurityRequirement(name = "jwt")
@CrossOrigin(origins = "*")
public class ProductController {

    /**
     * Product Service interface injection.
     */
    private final IProductService productService;

    /**
     * Method post to import all products from the API.
     * @return ResponseEntity with the status and message.
     */
    @Operation(summary = "Import all product from API",
            responses = {
                    @ApiResponse(responseCode = "201", description = "All product are saved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Product already exists",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/import")
    public ResponseEntity<Map<String, String>> importAllProducts() {
        productService.importAllProducts();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, MessageProduct.PRODUCT_IMPORT.getMessage()));
    }

    /**
     * Method post to import a product from the API.
     * @param id Product identifier.
     * @return ResponseEntity with the status, message and product data.
     */
    @Operation(summary = "Add a product from the API",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Product already exists",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/{id}")
    public ResponseEntity<Map<String, Object>> createProductById(@PathVariable Long id) {
        Product product = productService.createProductById(id);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.MESSAGE_KEY, MessageProduct.PRODUCT_CREATED.getMessage());
        response.put("data", product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Method post to create a product by the customer.
     * @param product Instance of Product class.
     * @return ResponseEntity with the status and message.
     */
    @Operation(summary = "Add a product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Product already exists",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody Product product) {
        productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.MESSAGE_KEY, MessageProduct.PRODUCT_CREATED.getMessage()));
    }

    /**
     * Method get to obtain all products.
     * @return ResponseEntity with the status, message and list products.
     */
    @Operation(summary = "Get a product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product returned",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        if (productList == null || productList.isEmpty()) {
            throw new NoDataFoundException();
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.MESSAGE_KEY, MessageProduct.PRODUCT_LIST.getMessage());
        response.put(Constants.DATA_KEY, productList);

        return ResponseEntity.ok(response);
    }

    /**
     * Method get to obtain a Product by id.
     * @param id Product identifier.
     * @return ResponseEntity with the status, message and product data.
     */
    @Operation(summary = "Get a product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User returned",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with provider id",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            throw new NoDataFoundException();
        }
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.MESSAGE_KEY, MessageProduct.PRODUCT_FIND.getMessage());
        response.put(Constants.DATA_KEY, product);

        return ResponseEntity.ok(response);
    }

    /**
     * Method put to update a Product by id with the customer's data.
     * @param id Product identifier.
     * @param product Instance Product class.
     * @return ResponseEntity with the status and message.
     */
    @Operation(summary = "Update a product",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Product updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "Product not found with provider id",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        if (product == null) {
            throw new NoDataFoundException();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(
                        Constants.MESSAGE_KEY,
                        MessageProduct.PRODUCT_UPDATED.getMessage()
        ));
    }



}
