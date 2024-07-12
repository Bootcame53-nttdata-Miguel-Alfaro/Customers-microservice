package com.nttdata.bank.customers.service.impl;

import com.nttdata.bank.customers.model.Credit;
import com.nttdata.bank.customers.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class LoanServiceImpl implements LoanService {

    private final WebClient.Builder webClientBuilder;

    public LoanServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Flux<Credit> findLoansByCustomerId(String customerId) {
        return webClientBuilder.build()
                .get()
                .uri("/credits/customer/" + customerId)
                .retrieve()
                .bodyToFlux(Credit.class);
    }
}
