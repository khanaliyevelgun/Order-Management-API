package com.example.mapper;

import com.example.dao.entity.CardEntity;
import com.example.dto.CardRequestDto;
import com.example.dto.CardResponseDto;

public class CardMapper {
    private CardMapper() {
    }
    public static CardEntity mapToCardEntity(CardRequestDto cardRequestDto){
         CardEntity card = new CardEntity();
         card.setBalance(cardRequestDto.getBalance());
         card.setCardNumber(cardRequestDto.getCardNumber());
         return card;
    }
    public static CardResponseDto mapEntityToCardResponseDto(CardEntity card){
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setBalance(card.getBalance());
        cardResponseDto.setCardNumber(card.getCardNumber());
        cardResponseDto.setId(card.getId());
        return cardResponseDto;
    }
}
