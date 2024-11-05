package com.t1.intensive.service;

import com.t1.intensive.exception.DataNotFoundException;
import com.t1.intensive.mapper.AccountMapper;
import com.t1.intensive.mapper.TransactionMapper;
import com.t1.intensive.model.dto.TransactionDto;
import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Transaction;
import com.t1.intensive.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;

    @Override
    @Transactional(readOnly = true)
    public TransactionDto getTransactionById(Long id) {
        return transactionMapper.toTransactionDto(transactionRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Transaction was not found")
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> findAllTransactionsByAccountId(Long accountId) {
        Account account = accountMapper.toAccountEntity(accountService.getAccountById(accountId));
        return transactionMapper.toTransactionDtoList(transactionRepository.findAllByAccount(account));
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto, Long accountId) {
        Account account = accountMapper.toAccountEntity(accountService.getAccountById(accountId));
        Transaction transaction = transactionMapper.toTransactionEntity(transactionDto);
        if (transaction.getTransactionTime() == null) {
            transaction.setTransactionTime(LocalDateTime.now());
        }
        transaction.setAccount(account);
        return transactionMapper.toTransactionDto(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id, Long accountId) {
        Account account = accountMapper.toAccountEntity(accountService.getAccountById(accountId));
        int rowsModified = transactionRepository.deleteByIdAndAccount(id, account);
        if (rowsModified == 0) {
            throw new DataNotFoundException("Record was not found or was already deleted");
        }
    }
}
