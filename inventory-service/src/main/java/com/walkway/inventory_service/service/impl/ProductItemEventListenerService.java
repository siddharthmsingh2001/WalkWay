package com.walkway.inventory_service.service.impl;


import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotCreateDto;
import com.walkway.inventory_service.dto.inventory_product_snapshot.InventoryProductSnapshotDto;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.inventory_service.service.InventoryProductSnapshotService;
import com.walkway.kafka_events.product_item_events.ProductItemCreatedEvent;
import com.walkway.kafka_events.product_item_events.ProductItemDeletedEvent;
import com.walkway.kafka_events.product_item_events.ProductItemEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductItemEventListenerService {

    private final InventoryProductSnapshotService inventoryProductSnapshotService;
    private final KafkaProductSnapshotMapper kafkaProductSnapshotMapper = new KafkaProductSnapshotMapper();

    @KafkaListener(
            topics = "${application.kafka.topics.product-item.name}",
            containerFactory = "productItemEventKafkaListenerContainerFactory"
    )
    public void handleProductItemEvent(ProductItemEvent productItemEvent) throws InventoryProductSnapshotNotFoundException, InventoryProductSnapshotAlreadyExistsException {
        log.info("KAFKA SERVICE: Received ProductItemEvent");

        switch (productItemEvent.getEventType()){
            case CREATED -> {
                ProductItemCreatedEvent productItemCreatedEvent = (ProductItemCreatedEvent) productItemEvent.getPayload();
                InventoryProductSnapshotDto inventoryProductSnapshotDto = saveInventoryProductSnapshot(productItemCreatedEvent);
                log.info("KAFKA SERVICE SUCCESS:\n Saved: ProductItemCreatedEvent{productCode: {}}",inventoryProductSnapshotDto.getProductCode());
            }
            case DELETED -> {
                ProductItemDeletedEvent productItemDeletedEvent = (ProductItemDeletedEvent) productItemEvent.getPayload();
                deleteInventoryProductSnapshot(productItemDeletedEvent);
                log.info("KAFKA SERVICE SUCCESS:\n Deleted ProductItemDeletedEvent{productCode: {}}",productItemDeletedEvent.getProductItemCode());
            }
            default -> log.warn("KAFKA SERVICE WARNING: Unknown event type {}", productItemEvent.getEventType());
        }
    }

    private InventoryProductSnapshotDto saveInventoryProductSnapshot(ProductItemCreatedEvent productItemCreatedEvent) throws InventoryProductSnapshotAlreadyExistsException {
        try {
            return inventoryProductSnapshotService.createInventoryProductSnapshot(
                    kafkaProductSnapshotMapper.toInventoryProductSnapshotCreateDto(productItemCreatedEvent)
            );
        } catch (InventoryProductSnapshotAlreadyExistsException e) {
            log.error("KAFKA SERVICE FAILURE:\nFailed to save : ProductItemCreatedEvent{productCode: {}}",productItemCreatedEvent.getProductItemCode());
            throw e;
        }
    }

    private void deleteInventoryProductSnapshot(ProductItemDeletedEvent productItemDeletedEvent) throws InventoryProductSnapshotNotFoundException {
        try{
            inventoryProductSnapshotService.deleteInventoryProductSnapshotByProductCode(
                    productItemDeletedEvent.getProductItemCode()
            );
        } catch (InventoryProductSnapshotNotFoundException e) {
            log.error("KAFKA SERVICE FAILURE:\nFailed to save : ProductItemDeletedEvent{productCode: {}}",productItemDeletedEvent.getProductItemCode());
            throw e;
        }
    }
}

class KafkaProductSnapshotMapper {
    public InventoryProductSnapshotCreateDto toInventoryProductSnapshotCreateDto(ProductItemCreatedEvent createdEvent) {
        return InventoryProductSnapshotCreateDto.builder()
                .productCode(createdEvent.getProductItemCode())
                .build();
    }
}