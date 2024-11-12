package com.programming.techie.mapper;

import com.programming.techie.dto.OrderLineItemsDto;
import com.programming.techie.entity.Order;
import com.programming.techie.entity.OrderLineItems;

public class OrderLineItemsMapper {

    public static OrderLineItemsDto mapToOrderLineItemDto(OrderLineItems orderLineItems){
        return new OrderLineItemsDto(
                orderLineItems.getId(),
                orderLineItems.getSkuCode(),
                orderLineItems.getPrice(),
                orderLineItems.getQuantity(),
                orderLineItems.getOrder() != null ? orderLineItems.getOrder().getId() : null
        );
    }

    public static OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        Order order = new Order();
        order.setId(orderLineItemsDto.getOrderId());
        return new OrderLineItems(
                orderLineItemsDto.getId(),
                orderLineItemsDto.getSkuCode(),
                orderLineItemsDto.getPrice(),
                orderLineItemsDto.getQuantity(),
                order
        );
    }
}