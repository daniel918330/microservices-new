package com.programming.techie.service.impl;

//import com.programming.techie.exception.ResourceNotFoundException;
import com.programming.techie.dto.OrderDto;
import com.programming.techie.entity.Order;
import com.programming.techie.entity.OrderLineItems;
import com.programming.techie.mapper.OrderMapper;
import com.programming.techie.repository.OrderRepository;
import com.programming.techie.repository.OrderLineItemsRepository;
import com.programming.techie.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;

    @Override
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        // 将 OrderDto 转换为 Order 实体
        Order order = OrderMapper.mapToOrder(orderDto);
        System.out.println("OrderLineItems List: " + order.getOrderLineItems());

        // 生成订单号
        order.setOrderNumber(UUID.randomUUID().toString());

        // 计算总数量和总价格
        order.setTotalQuantity(order.getOrderLineItems()
                .stream()
                .mapToInt(OrderLineItems::getQuantity)
                .sum()
        );
        order.setTotalPrice(order.getOrderLineItems()
                .stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        // 保存主订单 Order
        Order savedOrder = orderRepository.save(order);

        // 将保存后的 Order 赋值给每个 OrderLineItems
        for (OrderLineItems orderLineItem : savedOrder.getOrderLineItems()) {
            orderLineItem.setOrder(savedOrder);  // 确保 OrderLineItems 关联到已保存的 Order
            orderLineItemsRepository.save(orderLineItem);  // 保存 OrderLineItems
        }

        // 返回保存后的 OrderDto
        return OrderMapper.mapToOrderDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.map(OrderMapper::mapToOrderDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

