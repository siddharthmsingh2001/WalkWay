package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product_image.ProductImageCreateDto;
import com.walkway.product_service.dto.product_image.ProductImageDto;
import com.walkway.product_service.entity.ProductImage;
import com.walkway.product_service.entity.ProductItem;
import com.walkway.product_service.exception.product_image.ProductImageNotFoundException;
import com.walkway.product_service.repo.ProductImageRepository;
import com.walkway.product_service.repo.ProductItemRepository;
import com.walkway.product_service.service.ProductImageService;
import com.walkway.product_service.service.mapper.ProductImageMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageMapper productImageMapper;
    private final ProductImageRepository productImageRepository;
    private final ProductItemRepository productItemRepository;

    @Override
    @Transactional
    public ProductImageDto createProductImage(ProductImageCreateDto productImageCreateDto) throws ProductItemNotFoundException {
        ProductItem productItem = productItemRepository.findById(productImageCreateDto.getProductItemId()).orElseThrow(ProductItemNotFoundException::new);
        ProductImage productImage = productImageRepository.save(
                productImageMapper.toProductImage(productImageCreateDto, productItem)
        );
        return productImageMapper.toProductImageDto(productImage);
    }

    @Override
    public List<ProductImageDto> getProductImage() {
        return productImageRepository.findAll()
                .stream()
                .map(productImageMapper::toProductImageDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDto getProductImage(Integer productImageId) throws ProductImageNotFoundException {
        ProductImage productImage = productImageRepository.findById(productImageId).orElseThrow(ProductImageNotFoundException::new);
        return productImageMapper.toProductImageDto(productImage);
    }

    @Override
    @Transactional
    public void deleteProductImage(Integer productImageId) throws ProductImageNotFoundException {
        ProductImage productImage = productImageRepository.findById(productImageId).orElseThrow(ProductImageNotFoundException::new);
        productImageRepository.delete(productImage);
    }
}
