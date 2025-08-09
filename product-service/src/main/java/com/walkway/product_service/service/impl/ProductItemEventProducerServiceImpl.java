package com.walkway.product_service.service.impl;

import com.walkway.kafka_events.product_item_events.ProductItemEvent;
import com.walkway.kafka_events.product_item_events.ProductItemEventType;
import com.walkway.kafka_events.product_item_events.ProductItemCreatedEvent;
import com.walkway.kafka_events.product_item_events.ProductItemDeletedEvent;
import com.walkway.product_service.entity.ProductItem;
import com.walkway.product_service.service.ProductItemEventProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductItemEventProducerServiceImpl implements ProductItemEventProducerService {

    @Value("${application.kafka.topics.product-item.name}")
    private String productItemTopicName;

    private final KafkaTemplate<Integer, ProductItemEvent> kafkaTemplate;

    public void sendProductItemCreatedEvent(ProductItem productItem) {
        ProductItemCreatedEvent productItemCreatedEvent = ProductItemCreatedEvent.newBuilder()
                .setProductItemCode(productItem.getProductItemCode())
                .build();

        ProductItemEvent productItemEvent = ProductItemEvent.newBuilder()
                .setEventType(ProductItemEventType.CREATED)
                .setPayload(productItemCreatedEvent)
                .build();

        CompletableFuture<SendResult<Integer, ProductItemEvent>> future =
                kafkaTemplate.send(
                        productItemTopicName,
                        productItem.getProductItemCode(),
                        productItemEvent
                );

        future.whenComplete((result, cause) -> {
            if (cause == null) {
                log.info(
                        "KAFKA SERVICE SUCCESS: \nSent: ProductItemCreatedEvent{productCode: {}}\nWith Offset: {}",
                        ((ProductItemCreatedEvent) result.getProducerRecord().value().getPayload()).getProductItemCode(),
                        result.getRecordMetadata().offset()
                );
            } else {
                log.error(
                        "KAFKA SERVICE FAILURE: \nFailed to sent: ProductItemCreatedEvent{productCode: {}}\nDue to: {}",
                        ((ProductItemCreatedEvent) result.getProducerRecord().value().getPayload()).getProductItemCode(),
                        cause.getMessage()
                );
            }
        });
    }

    public void sendProductItemDeletedEvent(ProductItem productItem) {
        ProductItemDeletedEvent productItemDeletedEvent = ProductItemDeletedEvent.newBuilder()
                .setProductItemCode(productItem.getProductItemCode())
                .build();

        ProductItemEvent productItemEvent = ProductItemEvent.newBuilder()
                .setEventType(ProductItemEventType.DELETED)
                .setPayload(productItemDeletedEvent)
                .build();

        CompletableFuture<SendResult<Integer, ProductItemEvent>> future =
                kafkaTemplate.send(
                        productItemTopicName,
                        productItem.getProductItemCode(),
                        productItemEvent
                );

        future.whenComplete((result, cause) -> {
            if (cause == null) {
                log.info(
                        "KAFKA SERVICE SUCCESS: \nSent: ProductItemDeletedEvent{productCode: {}}\nWith Offset: {}",
                        ((ProductItemDeletedEvent) result.getProducerRecord().value().getPayload()).getProductItemCode(),
                        result.getRecordMetadata().offset()
                );
            } else {
                log.error(
                        "KAFKA SERVICE FAILURE: \nFailed to sent: ProductItemDeletedEvent{productCode: {}}\nDue to: {}",
                        ((ProductItemDeletedEvent) result.getProducerRecord().value().getPayload()).getProductItemCode(),
                        cause.getMessage()
                );
            }
        });
    }
}