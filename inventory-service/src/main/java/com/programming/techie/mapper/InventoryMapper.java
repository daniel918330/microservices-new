package com.programming.techie.mapper;

import com.programming.techie.dto.InventoryDto;
import com.programming.techie.entity.Inventory;

public class InventoryMapper {

    public static InventoryDto mapToInventoryDto(Inventory inventory){
        return new InventoryDto(
                inventory.getId(),
                inventory.getSkuCode(),
                inventory.getSkuQuantity()
        );
    }

    public static Inventory mapToInventory(InventoryDto inventoryDto) {
        return new Inventory(
                inventoryDto.getId(),
                inventoryDto.getSkuCode(),
                inventoryDto.getSkuQuantity()
        );
    }
}