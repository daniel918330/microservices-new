package com.programming.techie.controller;

import com.programming.techie.dto.OrderDto;
import com.programming.techie.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    // Add Order REST API with POST mapping and request body
    @PostMapping
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity<String>> createOrder(@RequestBody OrderDto orderDto) {
        return CompletableFuture.supplyAsync(() -> {
            orderService.placeOrder(orderDto);
            return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
        });
    }

    // Fallback method for the circuit breaker
    public CompletableFuture<ResponseEntity<String>> fallbackMethod(OrderDto orderDto, RuntimeException runtimeException) {
        return CompletableFuture.completedFuture(
                new ResponseEntity<>("Oops, order after some time please", HttpStatus.SERVICE_UNAVAILABLE)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id){
        OrderDto orderDto = orderService.getOrderById(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}