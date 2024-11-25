package com.t1.intensive.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.t1.intensive.model.enumeration.TransactionStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto implements Serializable {
    @JsonProperty("id")
    private Long id;
    @NotNull(message = "Количество обязательно для заполнения")
    @Positive
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("transaction_time")
    private LocalDateTime transactionTime;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("status")
    private TransactionStatus status;
}
