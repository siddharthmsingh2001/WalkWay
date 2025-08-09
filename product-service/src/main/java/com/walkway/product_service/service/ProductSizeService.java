package com.walkway.product_service.service;

import com.walkway.product_service.dto.product_size.ProductSizeCreateDto;
import com.walkway.product_service.dto.product_size.ProductSizeDto;
import com.walkway.product_service.dto.product_size.ProductSizeUpdateDto;
import com.walkway.product_service.exception.product_size.ProductSizeAlreadyExistsException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;

import java.util.List;

public interface ProductSizeService {

    ProductSizeDto createProductSize(ProductSizeCreateDto productSizeCreateDto) throws ProductSizeAlreadyExistsException;

    List<ProductSizeDto> getProductSize();

    ProductSizeDto getProductSize(Byte productSizeId) throws ProductSizeNotFoundException;

    ProductSizeDto updateProductSize(ProductSizeUpdateDto productSizeUpdateDto, Byte productSizeId) throws ProductSizeAlreadyExistsException, ProductSizeNotFoundException;

    void deleteProductSize(Byte productSizeId) throws ProductSizeNotFoundException;
}
