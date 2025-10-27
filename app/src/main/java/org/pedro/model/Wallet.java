package org.pedro.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class Wallet {
    @Getter
    private final BankService service;

    protected final List<Money> money;

    public Wallet(final BankService serviceType) {
        this.service = serviceType;
        this.money = new ArrayList<>();
    }

    protected List<Money> generateMoney(final long amount, final String description) {
        var history = new MoneyAudit(UUID.randomUUID(), service, description, OffsetDateTime.now());
        return Stream.generate(() -> new Money(history))
                .limit(amount)
                .toList();
    }

    public long getFunds() {
        return money.size();
    }

    /** 
     * Add existing Money objects, keeping their audit trail.
     */
    public void addMoney(final List<Money> money, final BankService service, final String description) {
        var history = new MoneyAudit(UUID.randomUUID(), service, description, OffsetDateTime.now());
        money.forEach(m -> m.addHistory(history));
        this.money.addAll(money);
    }

    /** 
     * Generate and add new Money entries. 
     */
    public void addMoney(final long amount, final String description) {
        var newMoney = generateMoney(amount, description);
        this.money.addAll(newMoney);
    }

    public List<Money> reduceMoney(final long amount) {
        List<Money> toRemove = new ArrayList<>();
        for (int i = 0; i < amount && !money.isEmpty(); i++) {
            toRemove.add(this.money.remove(0)); // safe for ArrayList
        }
        return toRemove;
    }

    public List<MoneyAudit> getFinancialTransactions() {
        return money.stream()
                .flatMap(m -> m.getHistory().stream())
                .toList();
    }
}
