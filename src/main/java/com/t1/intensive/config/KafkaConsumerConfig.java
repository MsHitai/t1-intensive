package com.t1.intensive.config;

import com.t1.intensive.model.dto.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        properties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, ClientShortDto> clientConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(ClientShortDto.class));
    }

    @Bean
    public ConsumerFactory<String, AccountDto> accountConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(AccountDto.class));
    }

    @Bean
    public ConsumerFactory<String, ErrorDto> errorConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(ErrorDto.class));
    }

    @Bean
    public ConsumerFactory<String, TransactionDto> transactionConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(TransactionDto.class));
    }

    @Bean
    public ConsumerFactory<String, TransactionAcceptDto> transactionAcceptConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(TransactionAcceptDto.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ClientShortDto>> clientFactory(
            ConsumerFactory<String, ClientShortDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ClientShortDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, AccountDto>> accountFactory(
            ConsumerFactory<String, AccountDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, AccountDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ErrorDto>> errorFactory(
            ConsumerFactory<String, ErrorDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ErrorDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TransactionDto>> transactionFactory(
            ConsumerFactory<String, TransactionDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, TransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TransactionAcceptDto>> transactionAcceptFactory(
            ConsumerFactory<String, TransactionAcceptDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, TransactionAcceptDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
