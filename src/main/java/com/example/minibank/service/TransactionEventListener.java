package com.example.minibank.service;

import com.example.minibank.event.TransactionCreatedEvent;
import com.example.minibank.entity.TransactionReport;
import com.example.minibank.repository.TransactionReportRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionEventListener {

    private static final Logger logger = LoggerFactory.getLogger(TransactionEventListener.class);
    private final TransactionReportRepository reportRepository;

    @Async
    @EventListener
    public void handleTransactionCreated(TransactionCreatedEvent event) {
        TransactionReport report = new TransactionReport();
        report.setTransactionId(event.getTransaction().getId());
        report.setStatus("PROCESSED");
        report.setDetails(String.format("Transaction %d processed from %s to %s amount %s",
                event.getTransaction().getId(),
                event.getTransaction().getFromAccount().getNumber(),
                event.getTransaction().getToAccount().getNumber(),
                event.getTransaction().getAmount()));

        reportRepository.save(report);
        logger.info("Transaction report created for txId={}, status={}", report.getTransactionId(), report.getStatus());
    }
}
