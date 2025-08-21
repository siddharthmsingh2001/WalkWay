package com.walkway.product_service.service;

import com.walkway.product_service.dto.product_image.ProductImageCreateDto;
import com.walkway.product_service.dto.product_image.ProductImageDto;
import com.walkway.product_service.dto.product_image.ProductImageUpdateDto;
import com.walkway.product_service.exception.product_image.ProductImageNotFoundException;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;

import java.util.List;

public interface ProductImageService {

    ProductImageDto createProductImage(ProductImageCreateDto productImageCreateDto) throws ProductItemNotFoundException;

    List<ProductImageDto> getProductImage();

    ProductImageDto getProductImage(Integer productImageId) throws ProductImageNotFoundException;

    void deleteProductImage(Integer productImageId) throws ProductImageNotFoundException;
}
