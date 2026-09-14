package com.ecommerce.order_service.dto;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        Integer quantity
) {
}
