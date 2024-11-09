package com.t1.intensive.consumer;

import com.t1.intensive.model.dto.AccountDto;
import com.t1.intensive.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.t1.intensive.util.ConstantsUtil.ACCOUNTS;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountListener {

    private final AccountService accountService;

    @KafkaListener(topics = ACCOUNTS, groupId = "t1-intensive", containerFactory = "accountFactory")
    void addAccount(AccountDto dto) {
        log.info("Received request to add account {}", dto);
        accountService.createAccount(dto, dto.getClientId());
    }
}