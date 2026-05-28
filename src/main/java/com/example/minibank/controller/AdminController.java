package com.example.minibank.controller;

import com.example.minibank.dto.TransactionReportDto;
import com.example.minibank.repository.TransactionReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TransactionReportRepository reportRepository;

    @GetMapping("/reports")
    public ResponseEntity<List<TransactionReportDto>> getReports() {
        List<TransactionReportDto> reports = reportRepository.findAll().stream()
                .map(report -> new TransactionReportDto(
                        report.getId(),
                        report.getTransactionId(),
                        report.getStatus(),
                        report.getProcessedAt(),
                        report.getDetails()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reports);
    }
}
