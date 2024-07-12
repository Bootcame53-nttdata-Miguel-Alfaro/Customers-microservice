package com.nttdata.bank.customers.service.impl;

import com.nttdata.bank.customers.model.Account;
import com.nttdata.bank.customers.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class AccountServiceImpl implements AccountService {
    private final WebClient.Builder webClientBuilder;

    public AccountServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Flux<Account> findAccountsByCustomerId(String customerId) {
        return webClientBuilder.build()
                .get()
                .uri("/accounts/customer/" + customerId)
                .retrieve()
                .bodyToFlux(Account.class);
    }
}
