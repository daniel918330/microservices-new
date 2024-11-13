package com.programming.techie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;
    private String skuCode;
    private Integer skuQuantity;
    // 添加 isInStock 方法
    public boolean isInStock() {
        return skuQuantity != null && skuQuantity > 0;
    }
}
