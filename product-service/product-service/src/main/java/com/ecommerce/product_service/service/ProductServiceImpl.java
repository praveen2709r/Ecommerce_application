package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.dto.UpdateRequest;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product=productMapper.toProduct(productRequest);
        Product savedProduct=productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toResponse).toList();
    }
    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Product not found")
        );
    }
    @Override
    public ProductResponse findById(Long id) {
        Product product=getProduct(id);
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateById(Long id, UpdateRequest productRequest) {
        Product product=getProduct(id);
        productMapper.updateProduct(product,productRequest);
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Override
    public void deleteById(Long id) {
        Product product=getProduct(id);
        productRepository.delete(product);
    }
}
