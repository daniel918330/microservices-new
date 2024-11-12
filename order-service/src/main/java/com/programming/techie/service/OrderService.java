package com.programming.techie.service;
import com.programming.techie.dto.OrderDto;

public interface OrderService {
    OrderDto placeOrder(OrderDto orderDto);

    OrderDto getOrderById(Long id);
}
