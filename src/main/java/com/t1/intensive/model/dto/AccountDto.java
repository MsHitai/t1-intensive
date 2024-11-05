package com.t1.intensive.model.dto;

import com.t1.intensive.model.enumeration.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Long id;
    @NotNull(message = "Может быть выбран либо DEBIT, либо CREDIT")
    private AccountType accountType;
    @NotNull(message = "Баланс обязателен для заполнения")
    @Positive
    private BigDecimal balance;
}
