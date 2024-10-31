package com.t1.intensive.controller;

import com.t1.intensive.annotation.LogDataSourceError;
import com.t1.intensive.model.dto.TransactionDto;
import com.t1.intensive.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить транзакцию по идентификатору")
    @LogDataSourceError
    public TransactionDto findTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping()
    @Operation(summary = "Получить все транзакции по идентификатору счета")
    @LogDataSourceError
    public List<TransactionDto> findAllTransactionsByAccountId(@RequestParam(name = "accountId") Long accountId) {
        return transactionService.findAllTransactionsByAccountId(accountId);
    }

    @PostMapping()
    @Operation(summary = "Добавление транзакции")
    @LogDataSourceError
    public TransactionDto createTransaction(@Valid @RequestBody TransactionDto transactionDto,
                                            @RequestParam(name = "accountId") Long accountId) {
        return transactionService.createTransaction(transactionDto, accountId);
    }

    @Operation(summary = "Удалить транзакцию по идентификатору")
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id, @RequestParam(name = "accountId") Long accountId) {
        transactionService.deleteTransaction(id, accountId);
    }
}
