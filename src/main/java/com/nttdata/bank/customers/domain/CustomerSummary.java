package com.nttdata.bank.customers.domain;

import com.nttdata.bank.customers.model.Account;
import com.nttdata.bank.customers.model.Credit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerSummary {
    private List<Account> accounts = new ArrayList<>();
    private List<Credit> credits = new ArrayList<>();

    public CustomerSummary(List<Account> accounts, List<Credit> credits) {
        this.accounts = accounts;
        this.credits = credits;
    }
}
