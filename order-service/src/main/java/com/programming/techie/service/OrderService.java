package com.programming.techie.service;
import com.programming.techie.dto.OrderDto;

public interface OrderService {
    void placeOrder(OrderDto orderDto);

    OrderDto getOrderById(Long id);
}
