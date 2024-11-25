package com.t1.intensive.service;

import com.t1.intensive.model.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto getAccountById(Long id);

    List<AccountDto> findAllAccountsByClientId(Long clientId);

    AccountDto createAccount(AccountDto accountDto, Long clientId);

    void deleteAccount(Long id, Long clientId);

    void updateAccount(AccountDto accountDto);

}
