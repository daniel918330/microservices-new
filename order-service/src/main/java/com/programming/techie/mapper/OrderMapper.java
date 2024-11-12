package com.programming.techie.mapper;

import com.programming.techie.dto.OrderDto;
import com.programming.techie.entity.Order;
import com.programming.techie.entity.OrderLineItems;

import java.util.Collections;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderNumber(),
                order.getTotalQuantity(),
                order.getTotalPrice(),
                order.getOrderLineItems().stream()
                        .map(OrderLineItemsMapper::mapToOrderLineItemDto)
                        .collect(Collectors.toList())
        );
    }

    public static Order mapToOrder(OrderDto orderDto) {
        Order order = new Order(
                orderDto.getId(),
                orderDto.getOrderNumber(),
                orderDto.getTotalQuantity(),
                orderDto.getTotalPrice(),
                orderDto.getOrderLineItemsDto() != null
                        ? orderDto.getOrderLineItemsDto().stream()
                        .map(OrderLineItemsMapper::mapToOrderLineItems)
                        .collect(Collectors.toList())
                        : Collections.emptyList()
        );

        // 处理关联关系
        order.getOrderLineItems().forEach(orderLineItem -> orderLineItem.setOrder(order));

        return order;
    }
}