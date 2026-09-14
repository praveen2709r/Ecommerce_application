package com.ecommerce.product_service.dto;

public record UpdateRequest(
        String name,
        Double price,
        Integer quantity
) {
}
