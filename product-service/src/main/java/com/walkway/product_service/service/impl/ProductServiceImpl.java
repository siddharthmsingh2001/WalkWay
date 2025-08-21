package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product.ProductCreateDto;
import com.walkway.product_service.dto.product.ProductDto;
import com.walkway.product_service.dto.product.ProductUpdateDto;
import com.walkway.product_service.entity.Product;
import com.walkway.product_service.entity.ProductCategory;
import com.walkway.product_service.entity.ProductGender;
import com.walkway.product_service.exception.product.ProductAlreadyExistsException;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_category.ProductCategoryNotFoundException;
import com.walkway.product_service.exception.product_gender.ProductGenderNotFoundException;
import com.walkway.product_service.repo.ProductCategoryRepository;
import com.walkway.product_service.repo.ProductGenderRepository;
import com.walkway.product_service.repo.ProductRepository;
import com.walkway.product_service.service.ProductService;
import com.walkway.product_service.service.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductGenderRepository productGenderRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDto createProduct(ProductCreateDto productCreateDto) throws ProductAlreadyExistsException, ProductCategoryNotFoundException, ProductGenderNotFoundException {
        if(productRepository.existsByProductName(productCreateDto.getProductName())){
            throw new ProductAlreadyExistsException();
        }
        ProductCategory productCategory = productCategoryRepository.findById(productCreateDto.getProductCategoryId()).orElseThrow(ProductCategoryNotFoundException::new);
        ProductGender productGender = productGenderRepository.findById(productCreateDto.getProductGenderId()).orElseThrow(ProductGenderNotFoundException::new);
        Product product = productRepository.save(
                productMapper.toProduct(productCreateDto, productCategory, productGender)
        );
        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> getProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProduct(Integer productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return productMapper.toProductDto(product);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductUpdateDto productUpdateDto, Integer productId) throws ProductNotFoundException, ProductCategoryNotFoundException, ProductGenderNotFoundException, ProductAlreadyExistsException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        if(productRepository.existsByProductName(productUpdateDto.getProductName())){throw new ProductAlreadyExistsException();}
        ProductCategory productCategory = productCategoryRepository.findById(productUpdateDto.getProductCategoryId()).orElseThrow(ProductCategoryNotFoundException::new);
        ProductGender productGender = productGenderRepository.findById(productUpdateDto.getProductGenderId()).orElseThrow(ProductGenderNotFoundException::new);
        return productMapper.toProductDto(
                productRepository.save(
                        productMapper.toProduct(product,productUpdateDto, productCategory, productGender)
                )
        );
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }
}
