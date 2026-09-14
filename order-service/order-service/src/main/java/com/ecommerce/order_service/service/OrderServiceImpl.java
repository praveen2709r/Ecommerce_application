package com.ecommerce.order_service.service;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;
import com.ecommerce.order_service.dto.ProductResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.entity.OrderStatus;
import com.ecommerce.order_service.mapper.OrderMapper;
import com.ecommerce.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    @Override
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name="productService",
    fallbackMethod = "productServiceFallback")
    public OrderResponse create(OrderRequest orderRequest) {
        System.out.println("➡️ Calling Product Service...");
        ProductResponse productResponse=productClient.getProduct(orderRequest.productId());
        System.out.println("✅ Product Service responded successfully");
        Order order=orderMapper.toOrder(orderRequest);
        order.setTotalPrice(
                BigDecimal.valueOf(productResponse.price() * orderRequest.quantity())
        );        order.setOrderStatus(OrderStatus.PLACED);
        Order savedOrder=orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }
    public OrderResponse productServiceFallback(
            OrderRequest orderRequest,
            Throwable throwable
    ){
        System.out.println("❌ FALLBACK EXECUTED");
        System.out.println("Reason: " + throwable.getMessage());
        throw new RuntimeException("Product service is currently unavailable");
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toResponse).toList();
    }

    private Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Order not found"));
    }
    @Override
    public OrderResponse findById(Long id) {
        Order order=getOrder(id);
        return orderMapper.toResponse(order);
    }

    @Override
    public void deleteById(Long id) {
        Order order=getOrder(id);
        orderRepository.delete(order);
    }
    @PostConstruct
    public void registerCircuitBreakerListener() {

        CircuitBreaker circuitBreaker =
                circuitBreakerRegistry.circuitBreaker("productService");

        circuitBreaker.getEventPublisher()
                .onStateTransition(event ->
                        System.out.println(
                                "🔄 CIRCUIT STATE: "
                                        + event.getStateTransition()
                        )
                );
    }
}
