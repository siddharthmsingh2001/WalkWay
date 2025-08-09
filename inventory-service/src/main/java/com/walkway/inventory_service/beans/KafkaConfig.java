package com.walkway.inventory_service.beans;

import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotAlreadyExistsException;
import com.walkway.inventory_service.exception.inventory_product_snapshot.InventoryProductSnapshotNotFoundException;
import com.walkway.kafka_events.product_item_events.ProductItemEvent;
import io.confluent.kafka.serializers.*;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.util.backoff.FixedBackOff;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value(value = "${application.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${application.kafka.group-id}")
    private String consumerGroupId;

    @Value(value = "${application.kafka.schema-registry-url}")
    private String schemaRegistryUrl;

    @Value(value = "${application.kafka.topics.product-item-dlt.name}")
    private String productItemDLTTopicName;

    @Value(value = "${application.kafka.topics.product-item-dlt.partitions}")
    private Integer productItemDLTPartitions;

    @Value(value = "${application.kafka.topics.product-item-dlt.replicas}")
    private Integer productItemDLTReplicas;

    @Value(value = "${application.kafka.topics.product-item-dlt.retention}")
    private String productItemDLTRetention;

    @Bean
    public ConsumerFactory<Integer, ProductItemEvent> productItemEventConsumerFactory(){
        Map<String, Object> config = new HashMap<>(){{
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
            put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
            put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
            put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
            put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, IntegerDeserializer.class);
            put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, KafkaAvroDeserializer.class);
        }};
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, ProductItemEvent> productItemEventKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Integer, ProductItemEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        DefaultErrorHandler productItemEventErrorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(productItemDLTKafkaTemplate()),
                new FixedBackOff(3000L,3)
        );
        productItemEventErrorHandler.addNotRetryableExceptions(InventoryProductSnapshotNotFoundException.class);
        productItemEventErrorHandler.addNotRetryableExceptions(InventoryProductSnapshotAlreadyExistsException.class);
        factory.setCommonErrorHandler(productItemEventErrorHandler);
        factory.setConsumerFactory(productItemEventConsumerFactory());
        return factory;
    }

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String,Object> configs = new HashMap<>(){{
            put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        }};
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicProductItemDLT(){
        return TopicBuilder
                .name(productItemDLTTopicName)
                .partitions(productItemDLTPartitions)
                .replicas(productItemDLTReplicas)
                .config(TopicConfig.RETENTION_MS_CONFIG, productItemDLTRetention)
                .build();
    }

    @Bean
    public ProducerFactory<Integer, ProductItemEvent> productItemEventDLTProducerFactory(){
        Map<String, Object> config = new HashMap<>(){{
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
            put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
            put(AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS, true);
        }};
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<Integer, ProductItemEvent> productItemDLTKafkaTemplate(){
        return new KafkaTemplate<>(productItemEventDLTProducerFactory());
    }
}