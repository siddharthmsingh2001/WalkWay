package com.walkway.product_service.service;

import java.util.List;

import com.walkway.product_service.dto.product_category.ProductCategoryCreateDto;
import com.walkway.product_service.dto.product_category.ProductCategoryDto;
import com.walkway.product_service.dto.product_category.ProductCategoryUpdateDto;
import com.walkway.product_service.exception.product_category.ProductCategoryAlreadyExistsException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;

public interface ProductCategoryService {

    ProductCategoryDto createProductCategory(ProductCategoryCreateDto productCategoryCreateDto) throws ProductCategoryAlreadyExistsException, ProductCategoryNotFoundException;

    List<ProductCategoryDto> getProductCategory();

    ProductCategoryDto getProductCategory(Byte productCategoryId) throws ProductCategoryNotFoundException;

    ProductCategoryDto updateProductCategory(Byte productCategoryId, ProductCategoryUpdateDto productCategoryUpdateDto) throws ProductCategoryNotFoundException, ProductCategoryAlreadyExistsException;

    void deleteProductCategory(Byte productCategoryId) throws ProductCategoryNotFoundException;
}
