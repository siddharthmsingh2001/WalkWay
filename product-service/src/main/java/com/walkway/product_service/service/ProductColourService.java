package com.walkway.product_service.service;

import java.util.List;
import com.walkway.product_service.dto.product_colour.ProductColourCreateDto;
import com.walkway.product_service.dto.product_colour.ProductColourDto;
import com.walkway.product_service.dto.product_colour.ProductColourUpdateDto;
import com.walkway.product_service.dto.product_gender.ProductGenderDto;
import com.walkway.product_service.exception.product_colour.ProductColourAlreadyExistsException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;


public interface ProductColourService {

    ProductColourDto createProductColour (ProductColourCreateDto productColourCreateDto) throws ProductColourAlreadyExistsException;

    List<ProductColourDto> getProductColour();

    ProductColourDto getProductColour(Byte productColourId) throws ProductColourNotFoundException;

    ProductColourDto updateProductColour(ProductColourUpdateDto productColourUpdateDto, Byte productColourId) throws ProductColourNotFoundException, ProductColourAlreadyExistsException;

    void deleteProductColour(Byte productColourId) throws ProductColourNotFoundException;

}
