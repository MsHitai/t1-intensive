package com.t1.intensive.service;

import com.t1.intensive.exception.DataNotFoundException;
import com.t1.intensive.mapper.AccountMapper;
import com.t1.intensive.mapper.ClientMapper;
import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.model.dto.ClientDto;
import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Client;
import com.t1.intensive.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ClientService clientService;

    private final AccountMapper accountMapper;
    private final ClientMapper clientMapper;

    @Override
    @Transactional(readOnly = true)
    public AccountDto getAccountById(Long id) {
        return accountMapper.toAccountDto(accountRepository.findByIdWithClient(id)
                .orElseThrow(() -> new DataNotFoundException("Record not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> findAllAccountsByClientId(Long clientId) {
        ClientDto client = clientService.getClientById(clientId);
        return accountMapper.toAccountDtoList(accountRepository.findAllByClient(clientMapper.toClientEntity(client)));
    }

    @Override
    @Transactional
    public AccountDto createAccount(AccountDto accountDto, Long clientId) {
        Client client = clientMapper.toClientEntity(clientService.getClientById(clientId));
        Account account = accountMapper.toAccountEntity(accountDto);
        account.setClient(client);
        accountRepository.save(account);
        return accountMapper.toAccountDto(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id, Long clientId) {
        Client client = clientMapper.toClientEntity(clientService.getClientById(clientId));
        int rowsModified = accountRepository.deleteByIdAndClient(id, client);
        if (rowsModified == 0) {
            throw new DataNotFoundException("Record was not found or was already deleted");
        }
    }

    @Override
    @Transactional
    public void updateAccount(AccountDto accountDto) {
        Account account = accountMapper.toAccountEntity(accountDto);
        accountRepository.save(account);
    }
}
