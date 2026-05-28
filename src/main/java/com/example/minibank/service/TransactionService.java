package com.example.minibank.service;

import com.example.minibank.dto.TransactionResponse;
import com.example.minibank.dto.TransferRequest;
import com.example.minibank.entity.Account;
import com.example.minibank.entity.Transaction;
import com.example.minibank.entity.Transaction.TransactionType;
import com.example.minibank.event.TransactionCreatedEvent;
import com.example.minibank.repository.AccountRepository;
import com.example.minibank.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public TransactionResponse makeTransfer(TransferRequest request) {
        Account fromAccount = accountRepository.findWithLockingById(getAccountId(request.getFromAccountNumber()))
                .orElseThrow(() -> new IllegalArgumentException("Source account not found: " + request.getFromAccountNumber()));
        Account toAccount = accountRepository.findWithLockingById(getAccountId(request.getToAccountNumber()))
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found: " + request.getToAccountNumber()));

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient funds for transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.TRANSFER);
        transaction.setDescription(request.getDescription());
        Transaction saved = transactionRepository.save(transaction);

        eventPublisher.publishEvent(new TransactionCreatedEvent(this, saved));
        return new TransactionResponse(
                saved.getId(),
                saved.getFromAccount().getNumber(),
                saved.getToAccount().getNumber(),
                saved.getAmount(),
                saved.getCreatedAt(),
                saved.getDescription()
        );
    }

    private Long getAccountId(String accountNumber) {
        return accountRepository.findByNumber(accountNumber)
                .map(Account::getId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountNumber));
    }
}
