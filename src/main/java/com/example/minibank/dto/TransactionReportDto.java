package com.example.minibank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class TransactionReportDto {
    private final Long id;
    private final Long transactionId;
    private final String status;
    private final Instant processedAt;
    private final String details;
}
