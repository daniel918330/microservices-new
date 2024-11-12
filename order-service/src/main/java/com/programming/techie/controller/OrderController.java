package com.programming.techie.controller;

import com.programming.techie.dto.OrderDto;
import com.programming.techie.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    // Add Employee REST API with POST mapping and request body
    @PostMapping
    public ResponseEntity<OrderDto> createEmployee(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.placeOrder(orderDto);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id){
        OrderDto orderDto = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}