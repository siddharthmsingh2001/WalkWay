package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product_image.ProductImageCreateDto;
import com.walkway.product_service.dto.product_image.ProductImageDto;
import com.walkway.product_service.entity.ProductImage;
import com.walkway.product_service.entity.ProductItem;
import org.springframework.stereotype.Service;

@Service
public class ProductImageMapper {

    public ProductImage toProductImage(ProductImageCreateDto productImageCreateDto, ProductItem productItem){
        return ProductImage.builder()
                .productItem(productItem)
                .productImageUrl(productImageCreateDto.getProductImageUrl())
                .build();
    }

    public ProductImageDto toProductImageDto(ProductImage productImage){
        return ProductImageDto.builder()
                .productImageId(productImage.getProductImageId())
                .productItemId(productImage.getProductItem().getProductItemId())
                .productImageUrl(productImage.getProductImageUrl())
                .productCreatedAt(productImage.getProductCreatedAt())
                .productUpdatedAt(productImage.getProductUpdatedAt())
                .build();
    }
}
