package com.procesos.parcial.mapper.impl;

import com.procesos.parcial.dto.ProductRequestDto;
import com.procesos.parcial.mapper.IProductRequestMapper;
import com.procesos.parcial.model.Product;
import com.procesos.parcial.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapperImpl implements IProductRequestMapper {

    @Override
    public Product toEntity(ProductRequestDto productRequest) {
        if (productRequest == null) {
            return null;
        }

        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setImage(productRequest.getImage());

        User user = new User();
        user.setId(productRequest.getUserId());
        product.setUser(user);

        return product;
    }
}
