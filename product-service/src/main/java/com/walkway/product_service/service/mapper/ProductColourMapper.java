package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product_colour.ProductColourCreateDto;
import com.walkway.product_service.dto.product_colour.ProductColourDto;
import com.walkway.product_service.entity.ProductColour;
import org.springframework.stereotype.Service;

@Service
public class ProductColourMapper {

    public ProductColour toProductColour(ProductColourCreateDto productColourCreateDto){
        return ProductColour.builder()
                .productColourName(productColourCreateDto.getProductColourName())
                .build();
    }

    public ProductColourDto toProductColourDto(ProductColour productColour){
        return ProductColourDto.builder()
                .productColourId(productColour.getProductColourId())
                .productColourName(productColour.getProductColourName())
                .productColourCreatedAt(productColour.getProductColourCreatedAt())
                .productColourUpdatedAt(productColour.getProductColourUpdatedAt())
                .build();
    }
}
