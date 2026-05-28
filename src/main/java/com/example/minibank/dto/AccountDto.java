package com.example.minibank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountDto {
    private final Long id;
    private final String number;
    private final String ownerEmail;
    private final BigDecimal balance;
}
