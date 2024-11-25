package com.t1.intensive.config;

import com.t1.intensive.model.dto.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1000);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, ClientShortDto> clientProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public ProducerFactory<String, AccountDto> accountProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public ProducerFactory<String, TransactionDto> transactionProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public ProducerFactory<String, ErrorDto> errorProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public ProducerFactory<String, TransactionAcceptDto> transactionAcceptDtoProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, ClientShortDto> clientKafkaTemplate(ProducerFactory<String, ClientShortDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, AccountDto> accountKafkaTemplate(ProducerFactory<String, AccountDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, TransactionDto> transactionKafkaTemplate(ProducerFactory<String, TransactionDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, ErrorDto> errorKafkaTemplate(ProducerFactory<String, ErrorDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTemplate<String, TransactionAcceptDto> transactionAcceptKafkaTemplate(ProducerFactory<String, TransactionAcceptDto> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
