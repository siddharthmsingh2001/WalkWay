package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product_gender.ProductGenderCreateDto;
import com.walkway.product_service.dto.product_gender.ProductGenderDto;
import com.walkway.product_service.dto.product_gender.ProductGenderUpdateDto;
import com.walkway.product_service.entity.ProductGender;
import com.walkway.product_service.exception.product_gender.ProductGenderAlreadyExistsException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;
import com.walkway.product_service.repo.ProductGenderRepository;
import com.walkway.product_service.service.ProductGenderService;
import com.walkway.product_service.service.mapper.ProductGenderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductGenderServiceImpl implements ProductGenderService {

    private final ProductGenderRepository productGenderRepository;
    private final ProductGenderMapper productGenderMapper;

    @Override
    @Transactional
    public ProductGenderDto createProductGender(ProductGenderCreateDto productGenderCreateDto) throws ProductGenderAlreadyExistsException {
        Optional<ProductGender> test = productGenderRepository.findByProductGenderName(productGenderCreateDto.getProductGenderName());
        if(test.isPresent()){throw new ProductGenderAlreadyExistsException();}
        ProductGender productGender = productGenderRepository.save(
                productGenderMapper.toProductGender(productGenderCreateDto)
        );
        return productGenderMapper.toProductGenderDto(productGender);
    }

    @Override
    public List<ProductGenderDto> getProductGender() {
        return productGenderRepository.findAll()
                .stream()
                .map(productGenderMapper::toProductGenderDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductGenderDto getProductGender(Byte productGenderId) throws ProductGenderNotFoundException {
        ProductGender productGender = productGenderRepository.findById(productGenderId).orElseThrow(ProductGenderNotFoundException::new);
        return productGenderMapper.toProductGenderDto(productGender);
    }

    @Override
    @Transactional
    public ProductGenderDto updateProductGender(ProductGenderUpdateDto productGenderUpdateDto, Byte productGenderId) throws ProductGenderNotFoundException, ProductGenderAlreadyExistsException {
        ProductGender productGender = productGenderRepository.findById(productGenderId).orElseThrow(ProductGenderNotFoundException::new);
        if(productGenderRepository.existsByProductGenderName(productGenderUpdateDto.getProductGenderName())){
            throw new ProductGenderAlreadyExistsException();
        }
        productGender.setProductGenderName(productGenderUpdateDto.getProductGenderName());
        return productGenderMapper.toProductGenderDto(productGenderRepository.save(productGender));
    }

    @Override
    @Transactional
    public void deleteProductGender(Byte productGenderId) throws ProductGenderNotFoundException {
        ProductGender productGender = productGenderRepository.findById(productGenderId).orElseThrow(ProductGenderNotFoundException::new);
        productGenderRepository.delete(productGender);
    }
}
