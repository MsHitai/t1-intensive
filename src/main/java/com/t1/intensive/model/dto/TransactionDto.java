package com.t1.intensive.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto {

    private Long id;
    @NotNull(message = "Количество обязательно для заполнения")
    @Positive
    private BigDecimal amount;
    private LocalDateTime transactionTime;
}
