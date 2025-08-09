package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product_category.ProductCategoryCreateDto;
import com.walkway.product_service.dto.product_category.ProductCategoryDto;
import com.walkway.product_service.dto.product_category.ProductCategoryUpdateDto;
import com.walkway.product_service.entity.ProductCategory;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryMapper {

    public ProductCategory toProductCategory(ProductCategoryCreateDto productCategoryCreateDto, ProductCategory productCategory){
        return ProductCategory.builder()
                .productCategoryName(productCategoryCreateDto.getProductCategoryName())
                .productParentCategory(productCategory)
                .productCategoryImage(productCategoryCreateDto.getProductCategoryImage())
                .build();
    }

    public ProductCategory toProductCategory(ProductCategory productCategory, ProductCategoryUpdateDto productCategoryUpdateDto, ProductCategory parentProductCategory){
        return productCategory
                .setProductCategoryName(productCategoryUpdateDto.getProductCategoryName())
                .setProductParentCategory(parentProductCategory)
                .setProductCategoryImage(productCategoryUpdateDto.getProductCategoryImage());
    }

     public ProductCategoryDto toProductCategoryDto(ProductCategory productCategory){
        return ProductCategoryDto.builder()
                .productCategoryId(productCategory.getProductCategoryId())
                .productCategoryName(productCategory.getProductCategoryName())
                .productParentCategoryId(
                        productCategory.getProductParentCategory()!=null?productCategory.getProductParentCategory().getProductCategoryId():null
                )
                .productCategoryImage(productCategory.getProductCategoryImage())
                .productCategoryCreatedAt(productCategory.getProductCategoryCreatedAt())
                .productCategoryUpdatedAt(productCategory.getProductCategoryUpdatedAt())
                .build();
     }
}
