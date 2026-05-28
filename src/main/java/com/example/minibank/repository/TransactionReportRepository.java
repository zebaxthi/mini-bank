package com.example.minibank.repository;

import com.example.minibank.entity.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionReport, Long> {
}
