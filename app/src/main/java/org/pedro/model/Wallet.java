package org.pedro.model;


import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

@ToString
public abstract  class Wallet {
    @Getter
    private final BankService service;

    protected final List<Money> money;

    public Wallet(final BankService serviceType){
        this.service = serviceType;
        this.money = new ArrayList<>();
    }

    protected  List<Money> generateMoney(final long amount, final String description){
            var history = new MoneyAudit(UUID.randomUUID(), service, description, OffsetDateTime.now());
            return Stream.generate(()->new Money(history))
            .limit(amount).toList();
    }
    public long getFunds(){
        return money.size();
    }
}
