package com.t1.intensive.model.dto;

import com.t1.intensive.model.enumeration.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Long id;
    private AccountType accountType;
    private BigDecimal balance;
}
