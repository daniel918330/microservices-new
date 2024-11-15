package com.programming.techie.service.impl;

import com.programming.techie.dto.InventoryDto;
import com.programming.techie.entity.Inventory;
//import com.programming.techie.exception.ResourceNotFoundException;
import com.programming.techie.mapper.InventoryMapper;
import com.programming.techie.repository.InventoryRepository;
import com.programming.techie.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryDto> checkInventory(List<String> skuCode){
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait End");
        List<Inventory> inventory = inventoryRepository.findBySkuCodeIn(skuCode);
        return inventory.stream().map((InventoryMapper::mapToInventoryDto))
                .collect(Collectors.toList());
    }
}

