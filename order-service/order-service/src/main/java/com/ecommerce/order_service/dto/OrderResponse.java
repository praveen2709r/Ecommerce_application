package com.ecommerce.order_service.dto;

import com.ecommerce.order_service.entity.OrderStatus;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        Long productId,
        Integer quantity,
        OrderStatus orderStatus,
        BigDecimal totalPrice
) {
}
