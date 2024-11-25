package com.t1.intensive.consumer;

import com.t1.intensive.model.dto.TransactionAcceptDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.t1.intensive.util.ConstantsUtil.TRANSACTIONS_ACCEPT;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionAcceptListener {

    @Value("${app.limit:5}")
    private int limitNumber;

    @KafkaListener(topics = TRANSACTIONS_ACCEPT, groupId = "t1-intensive", containerFactory = "transactionAcceptFactory")
    void checkTransactionFrequency(TransactionAcceptDto dto) {
        log.info("Received request to check transaction's frequency {}", dto);

    }
}
