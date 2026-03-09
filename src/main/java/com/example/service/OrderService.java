package com.example.service;

import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    void addOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long orderId);
    List<OrderResponseDto> getAllOrders();
    void deleteOrder(Long orderId);
}


