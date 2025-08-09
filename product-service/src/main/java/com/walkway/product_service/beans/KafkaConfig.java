package com.walkway.product_service.beans;

import com.walkway.kafka_events.product_item_events.ProductItemEvent;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerializer;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/*
    OPEN TERMINAL IN A DOCKER FOR A CONTAINER
    docker exec --workdir /opt/kafka/bin -it kafka sh

    LIST ALL TOPICS
    ./kafka-topics.sh --bootstrap-server localhost:9092 --list

    CONSUME A TOPIC
    ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic product-item-created --from-beginning

    DELETE A TOPIC
    ./kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic product-item-created
*/

@Configuration
public class KafkaConfig {

    @Value(value = "${application.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${application.kafka.schema-registry-url}")
    private String schemaRegistryUrl;

    @Value("${application.kafka.topics.product-item.name}")
    private String productItemTopicName;

    @Value("${application.kafka.topics.product-item.partitions}")
    private Integer productItemPartitions;

    @Value("${application.kafka.topics.product-item.replicas}")
    private Integer productItemReplicas;

    @Value("${application.kafka.topics.product-item.retention}")
    private String productItemRetention;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String,Object> configs = new HashMap<>(){{
            put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        }};
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicProductItem(){
        return TopicBuilder
                .name(productItemTopicName) // Kafka Topic Name
                .partitions(productItemPartitions)     // Number of partitions in a single Kafka Topic
                .replicas(productItemReplicas)        // How many kafka brokers hold a copy of this partition
                .config(TopicConfig.RETENTION_MS_CONFIG, productItemRetention)
                .build();
    }


    @Bean
    public ProducerFactory<Integer, ProductItemEvent> producerFactory(){
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
    public KafkaTemplate<Integer, ProductItemEvent> productItemKafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
