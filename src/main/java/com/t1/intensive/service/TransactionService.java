package com.t1.intensive.service;

import com.t1.intensive.model.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    TransactionDto getTransactionById(Long id);

    List<TransactionDto> findAllTransactionsByAccountId(Long accountId);

    TransactionDto createTransaction(TransactionDto transactionDto, Long accountId);

    void deleteTransaction(Long id, Long accountId);
}
