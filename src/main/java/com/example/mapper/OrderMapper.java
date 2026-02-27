package com.example.mapper;

import com.example.dao.entity.CardEntity;
import com.example.dao.entity.OrderEntity;
import com.example.dao.entity.ProductEntity;
import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;

import java.time.LocalDateTime;

public class OrderMapper {

    public static OrderEntity mapToOrderEntity(OrderRequestDto orderRequestDto){
        OrderEntity entity = new OrderEntity();
        entity.setOrderDate(LocalDateTime.now());
        entity.setQuantity(orderRequestDto.getProductCount());
        entity.setCardId(orderRequestDto.getCardId());
        entity.setProductId(orderRequestDto.getProductId());
        entity.setTotalAMount(orderRequestDto.getAmount());
        return entity;
    }
    public static OrderResponseDto mapEntityToOrderResponseDto(CardEntity card , ProductEntity product,OrderEntity order){
        var response = new OrderResponseDto();
        response.setAmount(order.getTotalAMount());
        response.setCardBalance(card.getBalance());
        response.setCardNumber(card.getCardNumber());
        response.setProductName(product.getName());
        return response;
    }



}

