package com.programming.techie.controller;

import com.programming.techie.dto.InventoryDto;
import com.programming.techie.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    // Example of a simple test GET mapping
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Controller is working!", HttpStatus.OK);
    }

    // Build Get All Inventory REST API
    @GetMapping("/{skuCode}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("skuCode") String skuCode) {
        boolean isStock= inventoryService.isInStock(skuCode);
        return new ResponseEntity<Boolean>(isStock, HttpStatus.OK);
    }
}