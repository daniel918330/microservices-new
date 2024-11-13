package com.programming.techie.service.impl;

//import com.programming.techie.exception.ResourceNotFoundException;
import com.programming.techie.dto.InventoryDto;
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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    @Transactional
    public void placeOrder(OrderDto orderDto) {
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

        // 获取 SKU 代码列表
        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        // 打印请求的 SKU 代码
        System.out.println("Requesting inventory data for SKU Codes: " + skuCodes);

        // 构建请求 URL，添加 SKU 代码作为查询参数
        String url = UriComponentsBuilder.fromHttpUrl("http://inventory-service/api/inventory/isInStock")
                .queryParam("skuCode", skuCodes.toArray(new String[0]))  // 将 SKU 代码作为查询参数传递
                .toUriString();  // 获取最终的 URL 字符串

        // 打印最终的请求 URL
        System.out.println("Requesting URL: " + url);

        // 使用 WebClient 发送请求并获取库存数据
        InventoryDto[] inventoryDtoArray = webClientBuilder.build().get()
                .uri(url)  // 使用构建好的 URL 发起请求
                .retrieve()
                .bodyToMono(InventoryDto[].class)
                .block();

        // 确保所有 SKU 都有返回数据，如果缺少某个 SKU，抛出异常
        for (String skuCode : skuCodes) {
            boolean skuFound = Arrays.stream(inventoryDtoArray)
                    .anyMatch(inventoryDto -> inventoryDto.getSkuCode().equals(skuCode));
            if (!skuFound) {
                throw new IllegalArgumentException("Missing inventory data for SKU Code: " + skuCode);
            }
        }

        // 检查所有商品是否都在库存中
        boolean allProductInStock = Arrays.stream(inventoryDtoArray)
                .allMatch(inventoryDto -> {
                    boolean inStock = inventoryDto.isInStock();
                    System.out.println("Checking SKU Code: " + inventoryDto.getSkuCode() + ", In Stock: " + inStock);
                    return inStock;
                });

        if (allProductInStock) {
            System.out.println("All products are in stock. Proceeding with order creation.");
            // 保存主订单
            Order savedOrder = orderRepository.save(order);
        } else {
            System.out.println("Some products are not in stock. Cannot place order.");
            throw new IllegalArgumentException("One or more products are out of stock.");
        }
    }

    @Override
    public OrderDto getOrderById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.map(OrderMapper::mapToOrderDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

