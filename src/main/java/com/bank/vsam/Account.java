package com.bank.vsam;
import java.math.BigDecimal;


public class Account {

    private String accountNo;
    private String name;
    private String accountType;
    private BigDecimal balance;
    private String status;

    public Account(String accountNo,
                   String name,
                   String accountType,
                   BigDecimal balance,
                   String status) {
        this.accountNo = accountNo;
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getName() {
        return name;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }
}
