package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product_gender.ProductGenderCreateDto;
import com.walkway.product_service.dto.product_gender.ProductGenderDto;
import com.walkway.product_service.entity.ProductGender;
import org.springframework.stereotype.Service;

@Service
public class ProductGenderMapper {

    public ProductGender toProductGender(ProductGenderCreateDto productGenderCreateDto){
        return ProductGender.builder()
                .productGenderName(productGenderCreateDto.getProductGenderName())
                .build();
    }

    public ProductGenderDto toProductGenderDto(ProductGender productGender){
        return ProductGenderDto.builder()
                .productGenderId(productGender.getProductGenderId())
                .productGenderName(productGender.getProductGenderName())
                .productGenderCreatedAt(productGender.getProductGenderCreatedAt())
                .productGenderUpdatedAt(productGender.getProductGenderUpdatedAt())
                .build();
    }
}
