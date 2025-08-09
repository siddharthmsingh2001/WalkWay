package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product_size.ProductSizeCreateDto;
import com.walkway.product_service.dto.product_size.ProductSizeDto;
import com.walkway.product_service.entity.ProductSize;
import org.springframework.stereotype.Service;

@Service
public class ProductSizeMapper {

    public ProductSize toProductSize(ProductSizeCreateDto productSizeCreateDto){
        return ProductSize.builder()
                .productSizeName(productSizeCreateDto.getProductSizeName())
                .productSizeOrder(productSizeCreateDto.getProductSizeOrder())
                .build();
    }

    public ProductSizeDto toProductSizeDto(ProductSize productSize){
        return ProductSizeDto.builder()
                .productSizeId(productSize.getProductSizeId())
                .productSizeName(productSize.getProductSizeName())
                .productSizeOrder(productSize.getProductSizeOrder())
                .productSizeCreatedAt(productSize.getProductSizeCreatedAt())
                .productSizeUpdatedAt(productSize.getProductSizeUpdatedAt())
                .build();
    }
}
