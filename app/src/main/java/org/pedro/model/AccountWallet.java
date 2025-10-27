package org.pedro.model;

import java.util.List;
import static org.pedro.model.BankService.ACCOUNT;

public class AccountWallet extends Wallet {

    private final List<String> pix;

    public AccountWallet(List<String> pix){
        super(ACCOUNT);
        this.pix = pix;
    }

      public AccountWallet(final long amount, final List<String> pix ){
        super(ACCOUNT);
        this.pix = pix;
        addMoney(amount,"Valor de criação da conta");
    }

    public void addMoney(final long amount, final String description){
        var money = generateMoney(amount, description);
    }

}
