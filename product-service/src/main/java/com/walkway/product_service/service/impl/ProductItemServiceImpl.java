package com.walkway.product_service.service.impl;

import com.walkway.product_service.dto.product_item.ProductItemCreateDto;
import com.walkway.product_service.dto.product_item.ProductItemDto;
import com.walkway.product_service.dto.product_item.ProductItemUpdateDto;
import com.walkway.product_service.entity.Product;
import com.walkway.product_service.entity.ProductColour;
import com.walkway.product_service.entity.ProductItem;
import com.walkway.product_service.entity.ProductSize;
import com.walkway.product_service.exception.product.ProductNotFoundException;
import com.walkway.product_service.exception.product_colour.ProductColourNotFoundException;
import com.walkway.product_service.exception.product_item.ProductItemAlreadyExistsException;
import com.walkway.product_service.exception.product_item.ProductItemNotFoundException;
import com.walkway.product_service.exception.product_size.ProductSizeNotFoundException;
import com.walkway.product_service.repo.ProductColourRepository;
import com.walkway.product_service.repo.ProductItemRepository;
import com.walkway.product_service.repo.ProductRepository;
import com.walkway.product_service.repo.ProductSizeRepository;
import com.walkway.product_service.service.ProductItemEventProducerService;
import com.walkway.product_service.service.ProductItemService;
import com.walkway.product_service.service.mapper.ProductItemMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemMapper productItemMapper;
    private final ProductItemRepository productItemRepository;
    private final ProductRepository productRepository;
    private final ProductColourRepository productColourRepository;
    private final ProductSizeRepository productSizeRepository;
    private final ProductItemEventProducerService productItemEventProducerService;

    @Override
    @Transactional
    public ProductItemDto createProductItem(ProductItemCreateDto productItemCreateDto) throws ProductNotFoundException, ProductColourNotFoundException, ProductSizeNotFoundException, ProductItemAlreadyExistsException {
        if(productItemRepository.existsByProductItemCode(productItemCreateDto.getProductItemCode())){
            throw new ProductItemAlreadyExistsException();
        }
        Product product = productRepository.findById(productItemCreateDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        ProductColour productColour = productColourRepository.findById(productItemCreateDto.getProductColourId()).orElseThrow(ProductColourNotFoundException::new);
        ProductSize productSize = productSizeRepository.findById(productItemCreateDto.getProductSizeId()).orElseThrow(ProductSizeNotFoundException::new);
        ProductItem productItem = productItemRepository.save(
                productItemMapper.toProductItem(productItemCreateDto,product,productColour,productSize)
        );
        productItemEventProducerService.sendProductItemCreatedEvent(productItem);
        return productItemMapper.toProductItemDto(productItem);
    }

    @Override
    public List<ProductItemDto> getProductItem() {
        return productItemRepository.findAll()
                .stream()
                .map(productItemMapper::toProductItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductItemDto getProductItem(Integer productItemId) throws ProductItemNotFoundException {
        ProductItem productItem = productItemRepository.findById(productItemId).orElseThrow(ProductItemNotFoundException::new);
        return productItemMapper.toProductItemDto(productItem);
    }

    @Override
    public ProductItemDto getProductItemByCode(Integer productItemCode) throws ProductItemNotFoundException {
        ProductItem productItem = productItemRepository.findByProductItemCode(productItemCode).orElseThrow(ProductItemNotFoundException::new);
        return productItemMapper.toProductItemDto(productItem);
    }

    @Override
    @Transactional
    public ProductItemDto updateProductItem(ProductItemUpdateDto productItemUpdateDto, Integer productItemId) throws ProductItemNotFoundException, ProductNotFoundException, ProductColourNotFoundException, ProductSizeNotFoundException {
        ProductItem productItem = productItemRepository.findById(productItemId).orElseThrow(ProductItemNotFoundException::new);
        Product product = productRepository.findById(productItemUpdateDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        ProductColour productColour = productColourRepository.findById(productItemUpdateDto.getProductColourId()).orElseThrow(ProductColourNotFoundException::new);
        ProductSize productSize = productSizeRepository.findById(productItemUpdateDto.getProductSizeId()).orElseThrow(ProductSizeNotFoundException::new);
        return productItemMapper.toProductItemDto(
                productItemRepository.save(
                        productItemMapper.toProductItem(
                                productItem,
                                productItemUpdateDto,
                                product,
                                productColour,
                                productSize
                        )
                )
        );
    }

    @Override
    @Transactional
    public void deleteProductItem(Integer productItemId) throws ProductItemNotFoundException {
        ProductItem productItem = productItemRepository.findById(productItemId).orElseThrow(ProductItemNotFoundException::new);
        productItemRepository.delete(productItem);
        productItemEventProducerService.sendProductItemDeletedEvent(productItem);
    }
}
