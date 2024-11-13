package com.programming.techie.service;

import com.programming.techie.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    List<InventoryDto> checkInventory(List<String> SkuCode);
}
