package com.example.controller;

import com.example.dto.ProductRequestDto;
import com.example.dto.ProductResponseDto;
import com.example.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        productService.createProduct(productRequestDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@Min(0) @PathVariable Long productId){
       return productService.getProductById(productId);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<ProductResponseDto> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size){
       return productService.getAllProducts(page,size);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{productId}")
    public void deleteProduct(@Min(0) @PathVariable Long productId){
        productService.deleteProduct(productId);
    }



}
