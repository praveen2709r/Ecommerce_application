package com.ecommerce.product_service.dto;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        Integer quantity
) {
}
