package com.example.controller;

import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody OrderRequestDto orderRequestDto){
        orderService.addOrder(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }
}
