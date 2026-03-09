package com.example.service;

import com.example.dao.entity.CardEntity;
import com.example.dto.CardRequestDto;
import com.example.dto.CardResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CardService {
    void createCard(CardRequestDto cardRequestDto);
    CardResponseDto getCardById(Long cardId);
    List<CardResponseDto> getAllCards();
    void deleteCard(Long cardId);
    CardEntity getCardEntityById(Long cardId);
}
