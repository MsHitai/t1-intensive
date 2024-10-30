package com.t1.intensive.service;

import com.t1.intensive.model.dto.AccountDto;

public interface AccountService {
    AccountDto findAccountById(Long id);
}
