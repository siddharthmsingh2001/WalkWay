package com.walkway.product_service.service;

import com.walkway.product_service.dto.product.ProductCreateDto;
import com.walkway.product_service.dto.product.ProductDto;
import com.walkway.product_service.dto.product.ProductUpdateDto;
import com.walkway.product_service.exception.product.ProductAlreadyExistsException;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;

import java.util.List;

public interface ProductService{

    ProductDto createProduct(ProductCreateDto productCreateDto) throws ProductAlreadyExistsException, ProductCategoryNotFoundException, ProductGenderNotFoundException;

    List<ProductDto> getProduct();

    ProductDto getProduct(Integer productId) throws ProductNotFoundException;

    ProductDto updateProduct(ProductUpdateDto productUpdateDto, Integer productId) throws ProductNotFoundException, ProductCategoryNotFoundException, ProductGenderNotFoundException, ProductAlreadyExistsException;

    void deleteProduct(Integer productId) throws ProductNotFoundException;
}
