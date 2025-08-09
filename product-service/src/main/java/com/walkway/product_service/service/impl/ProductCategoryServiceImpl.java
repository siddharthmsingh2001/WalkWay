package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product_category.ProductCategoryCreateDto;
import com.walkway.product_service.dto.product_category.ProductCategoryDto;
import com.walkway.product_service.dto.product_category.ProductCategoryUpdateDto;
import com.walkway.product_service.entity.ProductCategory;
import com.walkway.product_service.exception.product_category.ProductCategoryAlreadyExistsException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;
import com.walkway.product_service.repo.ProductCategoryRepository;
import com.walkway.product_service.service.ProductCategoryService;
import com.walkway.product_service.service.mapper.ProductCategoryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductCategoryDto createProductCategory(ProductCategoryCreateDto productCategoryCreateDto) throws ProductCategoryAlreadyExistsException, ProductCategoryNotFoundException {
        if(productCategoryRepository.existsByProductCategoryName(productCategoryCreateDto.getProductCategoryName())){
            throw new ProductCategoryAlreadyExistsException();
        }
        ProductCategory parentProductCategory = null;
        if(productCategoryCreateDto.getProductParentCategoryId()!=null){
            parentProductCategory = productCategoryRepository.findById(productCategoryCreateDto.getProductParentCategoryId()).orElseThrow(ProductCategoryNotFoundException::new);
        }
        ProductCategory productCategory = productCategoryRepository.save(
                productCategoryMapper.toProductCategory(productCategoryCreateDto,parentProductCategory)
        );
        return productCategoryMapper.toProductCategoryDto(productCategory);
    }

    @Override
    public List<ProductCategoryDto> getProductCategory() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategoryMapper::toProductCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDto getProductCategory(Byte productCategoryId) throws ProductCategoryNotFoundException {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).orElseThrow(ProductCategoryNotFoundException::new);
        return productCategoryMapper.toProductCategoryDto(productCategory);
    }

    @Override
    @Transactional
    public ProductCategoryDto updateProductCategory(Byte productCategoryId, ProductCategoryUpdateDto productCategoryUpdateDto) throws ProductCategoryNotFoundException, ProductCategoryAlreadyExistsException {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).orElseThrow(ProductCategoryNotFoundException::new);
        if(!productCategoryUpdateDto.getProductCategoryName().equals(productCategory.getProductCategoryName()) && productCategoryRepository.existsByProductCategoryName(productCategoryUpdateDto.getProductCategoryName())){
            throw new ProductCategoryAlreadyExistsException();
        }
        ProductCategory parentProductCategory = null;
        if(productCategoryUpdateDto.getProductParentCategoryId()!=null){
            parentProductCategory = productCategoryRepository.findById(productCategoryUpdateDto.getProductParentCategoryId()).orElseThrow(ProductCategoryNotFoundException::new);
        }
        return productCategoryMapper.toProductCategoryDto(
                productCategoryRepository.save(
                        productCategoryMapper.toProductCategory(productCategory,productCategoryUpdateDto,parentProductCategory)
                )
        );
    }

    @Override
    @Transactional
    public void deleteProductCategory(Byte productCategoryId) throws ProductCategoryNotFoundException {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).orElseThrow(ProductCategoryNotFoundException::new);
        productCategoryRepository.delete(productCategory);
    }
}
