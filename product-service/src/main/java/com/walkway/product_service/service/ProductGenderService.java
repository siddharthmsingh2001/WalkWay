package com.walkway.product_service.service;

import com.walkway.product_service.dto.product_gender.ProductGenderCreateDto;
import com.walkway.product_service.dto.product_gender.ProductGenderDto;
import com.walkway.product_service.dto.product_gender.ProductGenderUpdateDto;
import com.walkway.product_service.exception.product_gender.ProductGenderAlreadyExistsException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;

import java.util.List;

public interface ProductGenderService {

    ProductGenderDto createProductGender(ProductGenderCreateDto productGenderCreateDto) throws ProductGenderAlreadyExistsException;

    List<ProductGenderDto> getProductGender();

    ProductGenderDto getProductGender(Byte productGenderId) throws ProductGenderNotFoundException;

    ProductGenderDto updateProductGender(ProductGenderUpdateDto productGenderUpdateDto, Byte productGenderId) throws ProductGenderNotFoundException, ProductGenderAlreadyExistsException;

    void deleteProductGender(Byte productGenderId) throws ProductGenderNotFoundException;
}
