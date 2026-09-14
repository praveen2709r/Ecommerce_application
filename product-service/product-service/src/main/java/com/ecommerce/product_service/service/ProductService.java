package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.dto.UpdateRequest;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse updateById(Long id, UpdateRequest productRequest);
    void deleteById(Long id);
}
