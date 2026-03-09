package com.example.controller;

import com.example.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.constants.ErrorCodes.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleCardNotFound(CardNotFoundException ex){
        return new ExceptionResponse(CARD_NOT_FOUND_CODE,ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleOrderNotFound(OrderNotFoundException ex){
        return new ExceptionResponse(ORDER_NOT_FOUND_CODE, ex.getMessage());
    }
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleProductNotFound(ProductNotFoundException ex){
        return new ExceptionResponse(PRODUCT_NOT_FOUND_CODE, ex.getMessage());
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(CONFLICT)
    public ExceptionResponse handleInsufficientBalance(InsufficientBalanceException ex){
        return new ExceptionResponse(INSUFFICIENT_BALANCE_CODE, ex.getMessage());
    }
    @ExceptionHandler(InsufficientStockException.class)
    @ResponseStatus(CONFLICT)
    public ExceptionResponse handleInsufficientStock(InsufficientStockException ex){
        return new ExceptionResponse(INSUFFICIENT_STOCK_CODE, ex.getMessage());
    }
}
