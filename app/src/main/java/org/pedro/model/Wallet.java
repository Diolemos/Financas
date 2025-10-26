package org.pedro.model;


import lombok.Getter;
import lombok.ToString;

@ToString
public abstract  class Wallet {
    @Getter
    private final BankService BankServiceType;

    protected final List<Money> money;
}
