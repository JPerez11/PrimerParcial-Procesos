package com.procesos.parcial.mapper;

import com.procesos.parcial.dto.ProductRequestDto;
import com.procesos.parcial.model.Product;

public interface IProductRequestMapper {

    Product toEntity(ProductRequestDto productRequest);

}
