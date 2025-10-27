package org.pedro.repository;

import java.util.ArrayList;
import java.util.List;

import org.pedro.exception.AccountWithInvestmentException;
import org.pedro.exception.InvestmentNotFoundException;
import org.pedro.exception.NotEnoughFundsException;
import org.pedro.exception.WalletNotFoundException;
import org.pedro.model.AccountWallet;
import org.pedro.model.Investment;
import org.pedro.model.InvestmentWallet;

public class InvestmentRepository {
    private final List<InvestmentWallet> investments = new ArrayList<>();
    private final List<InvestmentWallet> wallets = new ArrayList<>();
    private long nextId ;

    public List<InvestmentWallet> listWallets() {
        return new ArrayList<>(wallets);
    }

    public InvestmentWallet deposit(final String pix, final long funds) {
        var wallet = findWalletByAccount(pix);
        wallet.addMoney(wallet.getAccount().reduceMoney(funds), wallet.getService(), "Depósito de investimento");
        return wallet;
    }

    public InvestmentWallet withdraw(final String pix, final long funds) {
        var wallet = findWalletByAccount(pix);
        if (wallet.getFunds() < funds) {
            throw new NotEnoughFundsException("Fundos insuficientes para realizar o saque");
        }
        wallet.getAccount().addMoney(wallet.reduceMoney(funds), wallet.getService(), "Saque de investimento");
        if (wallet.getFunds() == 0) {
            wallets.remove(wallet);
        }
        return wallet;
    }

    public void updateAmounts(final long percent) {
        wallets.forEach(wallet -> wallet.updateAmount(percent));
    }

    public Investment findById(final long id) {
        return investments.stream()
                .map(InvestmentWallet::getInvestment)
                .filter(investment -> investment.id() == id)
                .findFirst()
                .orElseThrow(() -> new InvestmentNotFoundException("Investimento não encontrado"));
    }

    public InvestmentWallet findWalletByAccount(final String pix) {
        return wallets.stream()
                .filter(wallet -> wallet.getAccount().getPix().contains(pix))
                .findFirst()
                .orElseThrow(() -> new WalletNotFoundException("Carteira não encontrada"));
    }

    public List<InvestmentWallet> listInvestments() {
        return new ArrayList<>(investments);
    }

    public Investment create(final long tax, final long daysToRescue, final long initialFunds) {
        Investment investment = new Investment(this.nextId, tax,  initialFunds);
        return investment;
    }

    public InvestmentWallet initInvestment(final AccountWallet account, final long id) {
        Investment investment = findById(id);
        
        List<AccountWallet> accountsInUse = new ArrayList<>();
        wallets.forEach(wallet -> accountsInUse.add(wallet.getAccount()));
        
        if (accountsInUse.contains(account)) {
            throw new AccountWithInvestmentException("Esta conta já possui investimentos");
        }
        
        if (account.getFunds() < investment.initalFunds()) {
            throw new NotEnoughFundsException("Fundos insuficientes para realizar o investimento");
        }
        
        InvestmentWallet investmentWallet = new InvestmentWallet(investment, account, investment.initalFunds());
        wallets.add(investmentWallet);
        return investmentWallet;
    }
}