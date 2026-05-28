package com.example.minibank.service;

import com.example.minibank.entity.Account;
import com.example.minibank.entity.User;
import com.example.minibank.repository.AccountRepository;
import com.example.minibank.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        if (userRepository.count() == 0) {
            User alice = new User();
            alice.setEmail("alice@example.com");
            alice.setPassword(passwordEncoder.encode("password123"));
            alice.setRoles(Set.of("ROLE_USER"));
            userRepository.save(alice);

            User bob = new User();
            bob.setEmail("bob@example.com");
            bob.setPassword(passwordEncoder.encode("password123"));
            bob.setRoles(Set.of("ROLE_USER"));
            userRepository.save(bob);

            Account account1 = new Account();
            account1.setNumber("ACC-1001");
            account1.setOwner(alice);
            account1.setBalance(BigDecimal.valueOf(1000));
            accountRepository.save(account1);

            Account account2 = new Account();
            account2.setNumber("ACC-1002");
            account2.setOwner(bob);
            account2.setBalance(BigDecimal.valueOf(500));
            accountRepository.save(account2);
        }
    }
}
