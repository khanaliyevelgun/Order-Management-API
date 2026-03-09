package com.example.mapper;
import com.example.dao.entity.CardEntity;
import com.example.dao.entity.OrderEntity;
import com.example.dao.entity.ProductEntity;
import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import java.time.LocalDateTime;
public final class OrderMapper {
    private OrderMapper() {}
    public static OrderEntity mapToOrderEntity(OrderRequestDto orderRequestDto,ProductEntity product,CardEntity card,Double totalAmount){
        OrderEntity order = new OrderEntity();
        order.setOrderDate(LocalDateTime.now());
        order.setQuantity(orderRequestDto.getProductCount());
        order.setCard(card);
        order.setProduct(product);
        order.setTotalAmount(totalAmount);
        return order;
    }
    public static OrderResponseDto mapEntityToOrderResponseDto(OrderEntity order){
        var response = new OrderResponseDto();
        response.setAmount(order.getTotalAmount());
        response.setCardBalance(order.getCard().getBalance());
        response.setCardNumber(order.getCard().getCardNumber());
        response.setProductName(order.getProduct().getName());
        return response;
    }
}

