package com.programming.techie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String orderNumber;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private List<OrderLineItemsDto> orderLineItemsDto;

}
