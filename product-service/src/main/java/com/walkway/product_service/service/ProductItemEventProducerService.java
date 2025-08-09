package com.walkway.product_service.service;

import com.walkway.product_service.entity.ProductItem;

public interface ProductItemEventProducerService {
    void sendProductItemCreatedEvent(ProductItem productItem);
    void sendProductItemDeletedEvent(ProductItem productItem);
}
