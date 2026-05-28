package com.example.minibank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@AllArgsConstructor
public class TransactionResponse {
    private final Long id;
    private final String fromAccountNumber;
    private final String toAccountNumber;
    private final BigDecimal amount;
    private final Instant createdAt;
    private final String description;
}
