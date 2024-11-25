package com.t1.intensive.consumer;

import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.model.dto.TransactionDto;
import com.t1.intensive.model.enumeration.AccountStatus;
import com.t1.intensive.model.enumeration.TransactionStatus;
import com.t1.intensive.service.AccountService;
import com.t1.intensive.service.ErrorLogService;
import com.t1.intensive.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.t1.intensive.util.ConstantsUtil.TRANSACTIONS;
import static com.t1.intensive.util.ConstantsUtil.TRANSACTIONS_RESULT;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionListener {

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final ErrorLogService errorLogService;

    @KafkaListener(topics = TRANSACTIONS, groupId = "t1-intensive", containerFactory = "transactionFactory")
    void addTransaction(TransactionDto dto) {
        log.info("Received request to add transaction {}", dto);
        try {
            if (TransactionStatus.OPEN.equals(dto.getStatus())) {
                dto.setStatus(TransactionStatus.REQUESTED);
                transactionService.requestTransaction(dto);
            }
            transactionService.createTransaction(dto, dto.getAccountId());
        } catch (Exception e) {
            String errorMessage = "An exception occurred while processing addTransaction";
            log.error(errorMessage, e);
            errorLogService.sendError(e, "transactionService.requestTransaction");
        }
    }

    @KafkaListener(topics = TRANSACTIONS_RESULT, groupId = "t1-intensive", containerFactory = "transactionFactory")
    void updateTransactionStatus(TransactionDto dto) {
        log.info("Received request to update transaction status {}", dto);
        try {
            if (TransactionStatus.BLOCKED.equals(dto.getStatus())) {
                AccountDto accountDto = accountService.getAccountById(dto.getAccountId());
                accountDto.setStatus(AccountStatus.BLOCKED);
                accountDto.setFrozenAmount(dto.getAmount());
                accountService.updateAccount(accountDto);
            }
            transactionService.updateTransaction(dto);
        } catch (Exception e) {
            String errorMessage = "An exception occurred while processing addTransaction";
            log.error(errorMessage, e);
            errorLogService.sendError(e, "transactionService.requestTransaction");
        }
    }
}
