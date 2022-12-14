package com.fidelity.model;

import java.math.BigDecimal;

public class UserAccount {
    String accountNumber;
    BigDecimal marginAvailable;
    BigDecimal marginUsed;
    AccountType accountType;
    User user;
    public UserAccount(String accountNumber, BigDecimal marginAvailable, BigDecimal marginUsed, AccountType accountType, User user) {
        this.accountNumber = accountNumber;
        this.marginAvailable = marginAvailable;
        this.marginUsed = marginUsed;
        this.accountType = accountType;
        this.user = user;
    }
}
