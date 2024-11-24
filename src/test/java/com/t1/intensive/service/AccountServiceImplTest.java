package com.t1.intensive.service;

import com.t1.intensive.exception.DataNotFoundException;
import com.t1.intensive.mapper.AccountMapper;
import com.t1.intensive.mapper.ClientMapper;
import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.model.dto.ClientDto;
import com.t1.intensive.model.entity.Account;
import com.t1.intensive.model.entity.Client;
import com.t1.intensive.model.enumeration.AccountStatus;
import com.t1.intensive.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private ClientMapper clientMapper;

    private Long accountId;
    private Long clientId;
    private AccountDto accountDto;
    private Account account;
    private ClientDto clientDto;
    private Client client;


    @BeforeEach
    void setUp() {
        accountId = 1L;
        clientId = 2L;
        accountDto = new AccountDto();
        accountDto.setId(accountId);
        clientDto = new ClientDto();
        clientDto.setId(clientId);
        account = new Account();
        account.setId(accountId);
        client = new Client();
        client.setId(clientId);
        account.setClient(client);
    }

    @Test
    void testGetAccountById() {
        when(accountRepository.findByIdWithClient(accountId)).thenReturn(Optional.of(account));
        when(accountMapper.toAccountDto(any())).thenReturn(accountDto);

        AccountDto result = accountService.getAccountById(accountId);

        assertEquals(accountDto, result);
    }

    @Test
    void testGetAllAccountsByClientId() {
        when(clientMapper.toClientEntity(clientDto)).thenReturn(client);
        when(accountRepository.findAllByClient(client)).thenReturn(List.of(account));
        when(accountMapper.toAccountDtoList(any())).thenReturn(List.of(accountDto));

        List<AccountDto> result = accountService.findAllAccountsByClientId(clientId);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testCreateAccount() {
        when(clientService.getClientById(clientId)).thenReturn(clientDto);
        when(clientMapper.toClientEntity(clientDto)).thenReturn(client);
        when(accountMapper.toAccountEntity(accountDto)).thenReturn(account);
        when(accountRepository.save(any())).thenReturn(account);
        when(accountMapper.toAccountDto(account)).thenReturn(accountDto);

        AccountDto result = accountService.createAccount(accountDto, clientId);

        assertEquals(accountDto, result);
    }

    @Test
    void testDeleteAccount() {
        when(clientService.getClientById(clientId)).thenReturn(clientDto);
        when(clientMapper.toClientEntity(clientDto)).thenReturn(client);
        when(accountRepository.deleteByIdAndClient(accountId, client)).thenReturn(1);

        assertDoesNotThrow(() -> accountService.deleteAccount(accountId, clientId));
    }

    @Test
    void testDeleteNonExistingAccount() {
        when(clientMapper.toClientEntity(clientDto)).thenReturn(client);
        doReturn(0).when(accountRepository).deleteByIdAndClient(-1L, client);

        assertThrows(DataNotFoundException.class, () -> accountService.deleteAccount(-1L, clientId));
    }

    @Test
    void testUpdateAccount() {
        accountDto.setStatus(AccountStatus.OPEN);
        when(accountMapper.toAccountEntity(accountDto)).thenReturn(account);
        account.setStatus(AccountStatus.OPEN);
        when(accountRepository.save(any())).thenReturn(account);

        assertDoesNotThrow(() -> accountService.updateAccount(accountDto));
    }
}