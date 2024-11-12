package com.programming.techie.service.impl;

import com.programming.techie.dto.InventoryDto;
import com.programming.techie.entity.Inventory;
//import com.programming.techie.exception.ResourceNotFoundException;
import com.programming.techie.mapper.InventoryMapper;
import com.programming.techie.repository.InventoryRepository;
import com.programming.techie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}

