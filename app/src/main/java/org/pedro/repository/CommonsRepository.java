package org.pedro.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.pedro.exception.NotEnoughFundsException;
import org.pedro.model.AccountWallet;
import static org.pedro.model.BankService.ACCOUNT;
import org.pedro.model.Money;
import org.pedro.model.MoneyAudit;

import static lombok.AccessLevel.PRIVATE;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class CommonsRepository {

    public static void checkFundsForTransaction(final AccountWallet source, final long amount) {
        if (source.getFunds() < amount){
            throw new NotEnoughFundsException("Sua conta não tem dinheiro o suficiente para realizar essa transação");
        }
    }
    public static List<Money> generateMoney(final UUID transactionId, final long funds, final String description){
        var history = new MoneyAudit(transactionId, ACCOUNT, description, OffsetDateTime.now());
        return Stream.generate(()-> new Money(history)).limit(funds).toList();
    }
}