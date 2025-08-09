package com.walkway.product_service.service.mapper;

import com.walkway.product_service.dto.product_item.ProductItemCreateDto;
import com.walkway.product_service.dto.product_item.ProductItemDto;
import com.walkway.product_service.dto.product_item.ProductItemUpdateDto;
import com.walkway.product_service.entity.Product;
import com.walkway.product_service.entity.ProductColour;
import com.walkway.product_service.entity.ProductItem;
import com.walkway.product_service.entity.ProductSize;
import org.springframework.stereotype.Service;

@Service
public class ProductItemMapper {

    public ProductItem toProductItem(ProductItemCreateDto productItemCreateDto, Product product, ProductColour productColour, ProductSize productSize){
        return ProductItem.builder()
                .product(product)
                .productColour(productColour)
                .productSize(productSize)
                .productItemOriginalPrice(productItemCreateDto.getProductItemOriginalPrice())
                .productItemSalePrice(productItemCreateDto.getProductItemSalePrice())
                .productItemCode(productItemCreateDto.getProductItemCode())
                .build();
    }

    public ProductItem toProductItem(ProductItem productItem, ProductItemUpdateDto productItemUpdateDto, Product product, ProductColour productColour, ProductSize productSize){
        return productItem
                .setProduct(product)
                .setProductColour(productColour)
                .setProductSize(productSize)
                .setProductItemOriginalPrice(productItemUpdateDto.getProductItemOriginalPrice())
                .setProductItemSalePrice(productItemUpdateDto.getProductItemSalePrice())
                .setProductItemCode(productItemUpdateDto.getProductItemCode());
    }

    public ProductItemDto toProductItemDto(ProductItem productItem){
        return ProductItemDto.builder()
                .productItemId(productItem.getProductItemId())
                .productId(productItem.getProduct().getProductId())
                .productColourId(productItem.getProductColour().getProductColourId())
                .productSizeId(productItem.getProductSize().getProductSizeId())
                .productItemOriginalPrice(productItem.getProductItemOriginalPrice())
                .productItemSalePrice(productItem.getProductItemSalePrice())
                .productItemCode(productItem.getProductItemCode())
                .productItemCreatedAt(productItem.getProductItemCreatedAt())
                .productItemUpdatedAt(productItem.getProductItemUpdatedAt())
                .build();
    }

}
