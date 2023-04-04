package com.procesos.parcial.service;

import com.procesos.parcial.exception.NoDataFoundException;
import com.procesos.parcial.model.Product;
import com.procesos.parcial.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final RestTemplate restTemplate;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void importAllProducts() {
        String url = "https://fakestoreapi.com/products/";
        Product[] product = restTemplate.getForObject(url, Product[].class);
        assert product != null;
        List<Product> productList =  Arrays.asList(product);
        productRepository.saveAll(productList);
    }

    @Override
    public Product createProductById(Long id) {
        String url = "https://fakestoreapi.com/products/"+id;
        Product product = restTemplate.getForObject(url, Product.class);
        assert product != null;
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productToUpdate = productRepository.findProductById(id);
        if (productToUpdate == null) {
            throw new NoDataFoundException();
        }

        productToUpdate.setTitle(product.getTitle() );
        productToUpdate.setPrice( product.getPrice() );
        productToUpdate.setDescription( product.getDescription() );
        productToUpdate.setCategory(product.getCategory() );
        productToUpdate.setImage(product.getImage() );

        return productToUpdate;
    }
}
