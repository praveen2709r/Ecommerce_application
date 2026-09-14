package com.ecommerce.order_service.service;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);
    List<OrderResponse> findAll();
    OrderResponse findById(Long id);
    void deleteById(Long id);
}
