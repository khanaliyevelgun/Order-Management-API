package com.example.service;

import com.example.dao.entity.ProductEntity;
import com.example.dto.ProductRequestDto;
import com.example.dto.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto getProductById(Long productId);
    void deleteProduct(Long productId);
    ProductEntity getProductEntityById(Long productId);
    Page<ProductResponseDto> getAllProducts(int page,int size);
}
