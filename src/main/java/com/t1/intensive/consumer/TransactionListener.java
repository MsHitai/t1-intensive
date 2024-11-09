package com.t1.intensive.consumer;

import com.t1.intensive.model.dto.TransactionDto;
import com.t1.intensive.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.t1.intensive.util.ConstantsUtil.TRANSACTIONS;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionListener {

    private final TransactionService transactionService;

    @KafkaListener(topics = TRANSACTIONS, groupId = "t1-intensive", containerFactory = "transactionFactory")
    void addAccount(TransactionDto dto) {
        log.info("Received request to add transaction {}", dto);
        transactionService.createTransaction(dto, dto.getAccountId());
    }
}
