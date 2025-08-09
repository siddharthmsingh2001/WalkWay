package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product_colour.ProductColourCreateDto;
import com.walkway.product_service.dto.product_colour.ProductColourDto;
import com.walkway.product_service.dto.product_colour.ProductColourUpdateDto;
import com.walkway.product_service.entity.ProductColour;
import com.walkway.product_service.exception.product_colour.ProductColourAlreadyExistsException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;
import com.walkway.product_service.repo.ProductColourRepository;
import com.walkway.product_service.service.ProductColourService;
import com.walkway.product_service.service.mapper.ProductColourMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductColourServiceImpl implements ProductColourService{

    private final ProductColourRepository productColourRepository;
    private final ProductColourMapper productColourMapper;

    @Override
    @Transactional
    public ProductColourDto createProductColour(ProductColourCreateDto productColourCreateDto) throws ProductColourAlreadyExistsException {
        Optional<ProductColour> test = productColourRepository.findByProductColourName(productColourCreateDto.getProductColourName());
        if(test.isPresent()){throw new ProductColourAlreadyExistsException();}
        ProductColour productColour = productColourRepository.save(
                productColourMapper.toProductColour(productColourCreateDto)
        );
        return productColourMapper.toProductColourDto(productColour);
    }

    @Override
    public List<ProductColourDto> getProductColour() {
        return productColourRepository.findAll()
                .stream()
                .map(productColourMapper::toProductColourDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductColourDto getProductColour(Byte productColourId) throws ProductColourNotFoundException {
        ProductColour productColour = productColourRepository.findById(productColourId).orElseThrow(ProductColourNotFoundException::new);
        return productColourMapper.toProductColourDto(productColour);
    }

    @Override
    @Transactional
    public ProductColourDto updateProductColour(ProductColourUpdateDto productColourUpdateDto, Byte productColourId) throws ProductColourNotFoundException, ProductColourAlreadyExistsException {
        ProductColour productColour = productColourRepository.findById(productColourId).orElseThrow(ProductColourNotFoundException::new);
        if(productColourRepository.existsByProductColourName(productColourUpdateDto.getProductColourName())){
            throw new ProductColourAlreadyExistsException();
        }
        productColour.setProductColourName(productColourUpdateDto.getProductColourName());
        return productColourMapper.toProductColourDto(productColourRepository.save(productColour));
    }

    @Override
    @Transactional
    public void deleteProductColour(Byte productColourId) throws ProductColourNotFoundException {
        ProductColour productColour = productColourRepository.findById(productColourId).orElseThrow(ProductColourNotFoundException::new);
        productColourRepository.delete(productColour);
    }
}
