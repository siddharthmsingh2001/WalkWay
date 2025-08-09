package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product.ProductCreateDto;
import com.walkway.product_service.dto.product.ProductDto;
import com.walkway.product_service.dto.product.ProductUpdateDto;
import com.walkway.product_service.entity.Product;
import com.walkway.product_service.entity.ProductCategory;
import com.walkway.product_service.entity.ProductGender;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductCreateDto productCreateDto, ProductCategory productCategory, ProductGender productGender){
        return Product.builder()
                .productName(productCreateDto.getProductName())
                .productCategory(productCategory)
                .productGender(productGender)
                .productDescription(productCreateDto.getProductDescription())
                .build();
    }

    public Product toProduct(Product product, ProductUpdateDto productUpdateDto, ProductCategory productCategory, ProductGender productGender){
        return product
                .setProductName(productUpdateDto.getProductName())
                .setProductCategory(productCategory)
                .setProductGender(productGender)
                .setProductDescription(productUpdateDto.getProductDescription());
    }

    public ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productCategoryId(product.getProductCategory().getProductCategoryId())
                .productGenderId(product.getProductGender().getProductGenderId())
                .productDescription(product.getProductDescription())
                .productCreatedAt(product.getProductCreatedAt())
                .productUpdatedAt(product.getProductUpdatedAt())
                .build();
    }
}
