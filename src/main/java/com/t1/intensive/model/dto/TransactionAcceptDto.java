package com.t1.intensive.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionAcceptDto implements Serializable {
    @JsonProperty("client_id")
    private Long clientId;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("transaction_id")
    private Long transactionId;
    @JsonProperty("transaction_amount")
    private BigDecimal transactionAmount;
    @JsonProperty("account_balance")
    private BigDecimal accountBalance;
    @JsonProperty("timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime timestamp;
}
