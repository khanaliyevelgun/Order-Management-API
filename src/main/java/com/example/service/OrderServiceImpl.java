package com.example.service;
import com.example.dao.entity.CardEntity;
import com.example.dao.entity.OrderEntity;
import com.example.dao.entity.ProductEntity;
import com.example.dao.repository.OrderRepository;
import com.example.dto.OrderRequestDto;
import com.example.dto.OrderResponseDto;
import com.example.exception.*;
import com.example.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.constants.ExceptionMessages.*;

@Service
@RequiredArgsConstructor


public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CardService cardService;



    @Transactional
    public void addOrder(OrderRequestDto orderRequestDto){
        var product = productService.getProductEntityById(orderRequestDto.getProductId());
        var card = cardService.getCardEntityById(orderRequestDto.getCardId());
        checkProductCount(product,orderRequestDto.getProductCount());
        Double totalAmount = product.getPrice()*orderRequestDto.getProductCount();
        checkCardBalance(card,totalAmount);
        var order = OrderMapper.mapToOrderEntity(orderRequestDto,product,card,totalAmount);
        product.setStock(product.getStock() - orderRequestDto.getProductCount());
        card.setBalance(card.getBalance() - totalAmount);
        orderRepository.save(order);
    }
    public OrderResponseDto getOrderById(Long orderId){
        var order = fetchOrderIfExists(orderId);
        return OrderMapper.mapEntityToOrderResponseDto(order);
    }
    public List<OrderResponseDto> getAllOrders(){
        return orderRepository.findAll().stream().map(OrderMapper::mapEntityToOrderResponseDto).toList();
    }
    public void deleteOrder(Long orderId){
       var order = fetchOrderIfExists(orderId);
        orderRepository.delete(order);
    }

    private void checkProductCount(ProductEntity product,Integer orderCount){
        if (product.getStock() < orderCount){
            throw new InsufficientStockException(NOT_AVAILABLE_STOCK);
        }
    }
    private void checkCardBalance(CardEntity card,Double amount){
        if (card.getBalance() < amount){
            throw new InsufficientBalanceException(INSUFFICIENT_BALANCE);
        }
    }
    private OrderEntity fetchOrderIfExists(Long orderId){
        var order = orderRepository.findById(orderId);
        if (order.isEmpty()){
            throw new OrderNotFoundException(ORDER_NOT_FOUND);
        }
        return order.get();
    }
}
