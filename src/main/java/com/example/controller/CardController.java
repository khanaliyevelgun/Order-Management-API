package com.example.controller;

import com.example.dto.CardRequestDto;
import com.example.dto.CardResponseDto;
import com.example.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@Valid @RequestBody CardRequestDto cardRequestDto){
        cardService.createCard(cardRequestDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{cardId}")
    public CardResponseDto getCardById(@Min(0) @PathVariable Long cardId){
        return cardService.getCardById(cardId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CardResponseDto> getAllCards(){
       return cardService.getAllCards();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{cardId}")
    public void deleteCard(@Min(0) @PathVariable Long cardId){
        cardService.deleteCard(cardId);
    }


}
