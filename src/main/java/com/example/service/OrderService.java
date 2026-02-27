package com.example.service;


import com.example.dao.entity.CardEntity;
import com.example.dao.entity.OrderEntity;
import com.example.dao.entity.ProductEntity;
import com.example.dao.repository.CardRepository;
import com.example.dao.repository.OrderRepository;
import com.example.dao.repository.ProductRepository;
import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import com.example.exception.ProductNotFoundException;
import com.example.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CardRepository cardRepository;

    public void addOrder(OrderRequestDto orderRequestDto){
        var entity = OrderMapper.mapToOrderEntity(orderRequestDto);
        var product = fetchProductIfExists(orderRequestDto.getProductId());
        checkProductCount(product,orderRequestDto.getProductCount());

        var card = fetchCardIfExists(orderRequestDto.getCardId());
        checkCardBalance(card,orderRequestDto.getAmount());
        product.setStock(product.getStock() - orderRequestDto.getProductCount());
        card.setBalance(card.getBalance() - orderRequestDto.getAmount());


        productRepository.save(product);
        cardRepository.save(card);
        orderRepository.save(entity);
    }

    public OrderResponseDto getOrderById(Long orderId){
        var order = fetchOrderIfExists(orderId);
        var card = fetchCardIfExists(order.getCardId());
        var product = fetchProductIfExists(order.getProductId());

        return OrderMapper.mapEntityToOrderResponseDto(card,product,order);



    }




    private ProductEntity fetchProductIfExists(Long productId){
         var product = productRepository.findById(productId);
         if (product.isEmpty()){
             throw new ProductNotFoundException("Product not found");
         }
         return product.get();
    }
    private void checkProductCount(ProductEntity product,Integer orderCount){
        if (product.getStock() < orderCount){
            throw new RuntimeException("Not available stock");

        }
    }
    private CardEntity fetchCardIfExists(Long cardId){
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()){
            throw new RuntimeException("Card not found");
        }
        return card.get();
    }
    private void checkCardBalance(CardEntity card,Double amount){
        if (card.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
    }
    private OrderEntity fetchOrderIfExists(Long orderId){
        var order = orderRepository.findById(orderId);
        if (order.isEmpty()){
            throw new RuntimeException("Card not found");
        }
        return order.get();
    }


}
