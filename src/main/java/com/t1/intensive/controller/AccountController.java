package com.t1.intensive.controller;

import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.service.AccountService;
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
    public AccountDto findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }
}
