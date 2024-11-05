package com.t1.intensive.controller;

import com.t1.intensive.annotation.LogDataSourceError;
import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    @Operation(summary = "Получить все счета по идентификатору клиента")
    @LogDataSourceError
    public List<AccountDto> findAllAccountsByClientId(@RequestParam(name = "clientId") Long clientId) {
        return accountService.findAllAccountsByClientId(clientId);
    }

    @PostMapping()
    @Operation(summary = "Добавление счета")
    @LogDataSourceError
    public AccountDto createAccount(@Valid @RequestBody AccountDto accountDto,
                                    @RequestParam(name = "clientId") Long clientId) {
        return accountService.createAccount(accountDto, clientId);
    }

    @Operation(summary = "Удалить счет по идентификатору")
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id, @RequestParam(name = "clientId") Long clientId) {
        accountService.deleteAccount(id, clientId);
    }
}
