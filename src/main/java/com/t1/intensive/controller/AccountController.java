package com.t1.intensive.controller;

import com.t1.intensive.annotation.LogDataSourceError;
import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить счет по идентификатору")
    @LogDataSourceError
    public AccountDto findAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}
