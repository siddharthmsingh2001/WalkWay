package com.walkway.product_service.service;

import com.walkway.product_service.dto.product_item.ProductItemCreateDto;
import com.walkway.product_service.dto.product_item.ProductItemDto;
import com.walkway.product_service.dto.product_item.ProductItemUpdateDto;
import com.walkway.product_service.entity.ProductItem;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;
import com.walkway.product_service.exception.product_item.ProductItemAlreadyExistsException;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductItemService {

    ProductItemDto createProductItem(ProductItemCreateDto productItemCreateDto) throws ProductNotFoundException, ProductColourNotFoundException, ProductSizeNotFoundException, ProductItemAlreadyExistsException;

    List<ProductItemDto> getProductItem();

    ProductItemDto getProductItem(Integer productItemId) throws ProductItemNotFoundException;

    ProductItemDto getProductItemByCode(Integer productItemCode) throws ProductItemNotFoundException;

    ProductItemDto updateProductItem(ProductItemUpdateDto productItemUpdateDto, Integer productItemId) throws ProductItemNotFoundException, ProductNotFoundException, ProductColourNotFoundException, ProductSizeNotFoundException;

    void deleteProductItem(Integer productItemId) throws ProductItemNotFoundException;
}
