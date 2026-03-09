package com.example.controller;

import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import com.example.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(CREATED)
    public void addOrder(@RequestBody @Valid OrderRequestDto orderRequestDto){
        orderService.addOrder(orderRequestDto);
    }
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }
}
