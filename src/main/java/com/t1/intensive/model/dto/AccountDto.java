package com.t1.intensive.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t1.intensive.model.enumeration.AccountStatus;
import com.t1.intensive.model.enumeration.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @NotNull(message = "Может быть выбран либо DEBIT, либо CREDIT")
    @JsonProperty("account_type")
    private AccountType accountType;
    @NotNull(message = "Баланс обязателен для заполнения")
    @Positive
    @JsonProperty("balance")
    private BigDecimal balance;
    @JsonProperty("client_id")
    private Long clientId;
    @JsonProperty("status")
    private AccountStatus status;
    @JsonProperty("frozen_amount")
    private BigDecimal frozenAmount;
}
