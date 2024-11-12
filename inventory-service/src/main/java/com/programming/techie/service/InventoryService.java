package com.programming.techie.service;

import com.programming.techie.dto.InventoryDto;

import java.util.List;

public interface InventoryService {
    boolean isInStock(String SkuCode);
}
