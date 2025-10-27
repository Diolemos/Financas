package org.pedro.repository;

import org.pedro.model.AccountWallet;
import org.pedro.exception.AccountNotfoundException;
import org.pedro.exception.NotEnoughFundsException;
import java.util.List;
import java.util.ArrayList;

public class AccountRepository {
    private List<AccountWallet> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    public AccountWallet findByPix(final String pix) {
        return accounts.stream()
                .filter(account -> account.getPix().equals(pix))
                .findFirst()
                .orElseThrow(()->new AccountNotfoundException("A conta com a chave pix '"+ pix +"' não existe ou foi encerrada."));
    }

    public AccountWallet create(final List<String> pix, final long amount) {
        AccountWallet newAccount = new AccountWallet(amount,pix);
        accounts.add(newAccount);
        return newAccount;
    }

    public void deposit(final String pix, final long fundsAmount) {
        AccountWallet account = findByPix(pix);
        account.addMoney(fundsAmount, "Depósito");
    }

    public long withdraw(final String pix, final long amount) {
        AccountWallet account = findByPix(pix);
        if (account.getFunds() < amount) {
            throw new NotEnoughFundsException("Fundos insuficientes para realizar o saque");
        }
        account.reduceMoney(amount);
        return amount;
    }

    public void transferMoney(final String sourcePix, final String targetPix, final long amount) {
        AccountWallet sourceAccount = findByPix(sourcePix);
        AccountWallet targetAccount = findByPix(targetPix);
        
        if (sourceAccount.getFunds() < amount) {
            throw new NotEnoughFundsException("Fundos insuficientes para realizar a transferência");
        }
        
        sourceAccount.reduceMoney(amount);
        targetAccount.addMoney(amount, "Transferência recebida de " + sourcePix);
    }
}
