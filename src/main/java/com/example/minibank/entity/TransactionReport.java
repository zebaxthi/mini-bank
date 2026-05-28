package com.example.minibank.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "transaction_reports")
@Getter
@Setter
@NoArgsConstructor
public class TransactionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long transactionId;

    private String status;

    private Instant processedAt = Instant.now();

    @Column(length = 1000)
    private String details;
}
