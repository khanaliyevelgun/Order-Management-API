package com.example.service;

import com.example.dao.entity.CardEntity;
import com.example.dao.repository.CardRepository;
import com.example.dto.CardRequestDto;
import com.example.dto.CardResponseDto;
import com.example.exception.CardNotFoundException;
import com.example.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.constants.ExceptionMessages.CARD_NOT_FOUND;
import static com.example.mapper.CardMapper.mapEntityToCardResponseDto;
import static com.example.mapper.CardMapper.mapToCardEntity;
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{
    private final CardRepository cardRepository;
    @Override
    public void createCard(CardRequestDto cardRequestDto) {
       cardRepository.save(mapToCardEntity(cardRequestDto));
    }

    @Override
    public CardResponseDto getCardById(Long cardId) {
        return mapEntityToCardResponseDto(getCardEntityById(cardId));
    }

    @Override
    public List<CardResponseDto> getAllCards() {
        return cardRepository.findAll().stream().map(CardMapper::mapEntityToCardResponseDto).toList();
    }

    @Override
    public void deleteCard(Long cardId) {
        var card = getCardEntityById(cardId);
        cardRepository.delete(card);
    }
    public CardEntity getCardEntityById(Long cardId){
        var card = cardRepository.findById(cardId);
        if (card.isEmpty()){
            throw new CardNotFoundException(CARD_NOT_FOUND);
        }
        return card.get();
    }
}
