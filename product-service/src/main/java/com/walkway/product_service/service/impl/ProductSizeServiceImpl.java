package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product_size.ProductSizeCreateDto;
import com.walkway.product_service.dto.product_size.ProductSizeDto;
import com.walkway.product_service.dto.product_size.ProductSizeUpdateDto;
import com.walkway.product_service.entity.ProductSize;
import com.walkway.product_service.exception.product_size.ProductSizeAlreadyExistsException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;
import com.walkway.product_service.repo.ProductSizeRepository;
import com.walkway.product_service.service.ProductSizeService;
import com.walkway.product_service.service.mapper.ProductSizeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSizeServiceImpl implements ProductSizeService {

    private final ProductSizeRepository productSizeRepository;
    private final ProductSizeMapper productSizeMapper;

    @Override
    @Transactional
    public ProductSizeDto createProductSize(ProductSizeCreateDto productSizeCreateDto) throws ProductSizeAlreadyExistsException {
        Optional<ProductSize> productSizeName = productSizeRepository.findByProductSizeName(productSizeCreateDto.getProductSizeName());
        if(productSizeName.isPresent()){throw new ProductSizeAlreadyExistsException("Product Size with given name already exists");}
        Optional<ProductSize> productSizeOrder = productSizeRepository.findByProductSizeOrder(productSizeCreateDto.getProductSizeOrder());
        if(productSizeOrder.isPresent()){throw new ProductSizeAlreadyExistsException("Product Size with given order already exists");}
        ProductSize productSize = productSizeRepository.save(
                productSizeMapper.toProductSize(productSizeCreateDto)
        );
        return productSizeMapper.toProductSizeDto(productSize);
    }

    @Override
    public List<ProductSizeDto> getProductSize() {
        return productSizeRepository.findAll()
                .stream()
                .map(productSizeMapper::toProductSizeDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductSizeDto getProductSize(Byte productSizeId) throws ProductSizeNotFoundException {
        ProductSize productSize = productSizeRepository.findById(productSizeId).orElseThrow(ProductSizeNotFoundException::new);
        return productSizeMapper.toProductSizeDto(productSize);
    }

    @Override
    @Transactional
    public ProductSizeDto updateProductSize(ProductSizeUpdateDto productSizeUpdateDto, Byte productSizeId) throws ProductSizeAlreadyExistsException, ProductSizeNotFoundException {
        ProductSize productSize = productSizeRepository.findById(productSizeId).orElseThrow(ProductSizeNotFoundException::new);
        if(!productSize.getProductSizeName().equals(productSizeUpdateDto.getProductSizeName()) && productSizeRepository.existsByProductSizeName(productSizeUpdateDto.getProductSizeName())){
            throw new ProductSizeAlreadyExistsException("Product Size with given name already exists");
        }
        if(productSize.getProductSizeId()!=productSizeUpdateDto.getProductSizeOrder() &&productSizeRepository.existsByProductSizeOrder(productSizeUpdateDto.getProductSizeOrder())){
            throw new ProductSizeAlreadyExistsException("Product Size with given order already exists");
        }
        productSize.setProductSizeName(productSizeUpdateDto.getProductSizeName());
        productSize.setProductSizeOrder(productSizeUpdateDto.getProductSizeOrder());
        return productSizeMapper.toProductSizeDto(productSizeRepository.save(productSize));
    }

    @Override
    @Transactional
    public void deleteProductSize(Byte productSizeId) throws ProductSizeNotFoundException {
        ProductSize productSize = productSizeRepository.findById(productSizeId).orElseThrow(ProductSizeNotFoundException::new);
        productSizeRepository.delete(productSize);
    }
}
