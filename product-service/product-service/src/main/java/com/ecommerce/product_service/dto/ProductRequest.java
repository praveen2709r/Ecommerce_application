package com.ecommerce.product_service.dto;

public record ProductRequest(
        String name,
        Double price,
        Integer quantity
) {
}
