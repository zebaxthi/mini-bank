package com.example.minibank.service;

import com.example.minibank.dto.AccountDto;
import com.example.minibank.dto.CreateAccountRequest;
import com.example.minibank.entity.Account;
import com.example.minibank.entity.User;
import com.example.minibank.repository.AccountRepository;
import com.example.minibank.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Transactional
    public AccountDto createAccount(CreateAccountRequest request) {
        User owner = userRepository.findByEmail(request.getOwnerEmail())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found: " + request.getOwnerEmail()));

        Account account = new Account();
        account.setOwner(owner);
        account.setNumber(request.getNumber());
        account.setBalance(request.getInitialBalance());

        Account saved = accountRepository.save(account);
        return toDto(saved);
    }

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Account getAccountByNumber(String number) {
        return accountRepository.findByNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + number));
    }

    private AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), account.getNumber(), account.getOwner().getEmail(), account.getBalance());
    }
}
