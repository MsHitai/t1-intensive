package com.t1.intensive.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.model.enumeration.AccountStatus;
import com.t1.intensive.model.enumeration.AccountType;
import com.t1.intensive.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AccountService service;

    private Long accountId;
    private Long clientId;
    private AccountDto accountDto;

    @BeforeEach
    void setUp() {
        accountId = 1L;
        clientId = 2L;
        accountDto = new AccountDto();
        accountDto.setId(accountId);
    }

    @Test
    void testFindAccountById() throws Exception {
        when(service.getAccountById(accountId)).thenReturn(accountDto);

        mvc.perform(get("/accounts/{id}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(accountId), Long.class));
    }

    @Test
    void testFindAllAccountsByClientId() throws Exception {
        when(service.findAllAccountsByClientId(clientId)).thenReturn(List.of(accountDto));

        mvc.perform(get("/accounts")
                        .param("clientId", String.valueOf(clientId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(accountId), Long.class));
    }

    @Test
    void testCreateAccount() throws Exception {
        accountDto.setStatus(AccountStatus.OPEN);
        accountDto.setBalance(new BigDecimal("122.1212"));
        accountDto.setClientId(clientId);
        accountDto.setAccountType(AccountType.DEBIT);
        when(service.createAccount(accountDto, clientId)).thenReturn(accountDto);

        mvc.perform(post("/accounts")
                        .param("clientId", String.valueOf(clientId))
                        .content(mapper.writeValueAsString(accountDto))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(accountId), Long.class))
                .andExpect(jsonPath("$.status", is(accountDto.getStatus().getValue())));
    }

    @Test
    void testDeleteAccount() throws Exception {
        mvc.perform(delete("/accounts/{id}", accountId)
                        .param("clientId", String.valueOf(clientId)))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAccount(accountId, clientId);
    }
}