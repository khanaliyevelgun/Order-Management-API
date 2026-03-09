package com.example.mapper;

import com.example.dao.entity.ProductEntity;
import com.example.dto.ProductRequestDto;
import com.example.dto.ProductResponseDto;

public class ProductMapper {
    private ProductMapper() {
    }

    public static ProductEntity mapToProductEntity(ProductRequestDto productRequestDto){
        ProductEntity entity = new ProductEntity();
        entity.setStock(productRequestDto.getStock());
        entity.setName(productRequestDto.getName());
        entity.setPrice(productRequestDto.getPrice());
        return entity;
    }
    public static ProductResponseDto mapEntityToProductResponse(ProductEntity productEntity){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(productEntity.getName());
        productResponseDto.setPrice(productEntity.getPrice());
        productResponseDto.setStock(productEntity.getStock());
        productResponseDto.setId(productEntity.getId());
        return productResponseDto;
    }
}
