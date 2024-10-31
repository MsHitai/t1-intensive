package com.t1.intensive.service;

import com.t1.intensive.exception.DataNotFoundException;
import com.t1.intensive.mapper.AccountMapper;
import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long id) {
        return accountMapper.toAccountDto(accountRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Record not found")));
    }
}
