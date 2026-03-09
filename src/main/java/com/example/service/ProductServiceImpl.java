package com.example.service;

import com.example.dao.entity.ProductEntity;
import com.example.dao.repository.ProductRepository;
import com.example.dto.ProductRequestDto;
import com.example.dto.ProductResponseDto;
import com.example.exception.ProductNotFoundException;
import com.example.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.constants.ExceptionMessages.PRODUCT_NOT_FOUND;
import static com.example.mapper.ProductMapper.mapEntityToProductResponse;
import static com.example.mapper.ProductMapper.mapToProductEntity;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        productRepository.save(mapToProductEntity(productRequestDto));
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        return mapEntityToProductResponse(getProductEntityById(productId));
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAll(pageable).map(ProductMapper::mapEntityToProductResponse);
    }


    @Override
    public void deleteProduct(Long productId) {
        var product = getProductEntityById(productId);
        productRepository.delete(product);
    }

    public ProductEntity getProductEntityById(Long productId){
        var product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
        return product.get();
    }



}
