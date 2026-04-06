package edu.sec.banking.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Account {
    private final Long id;
    private String accHolderName;
    private Double accountBalance;
    private String accountType;

    private static final String DEFAULT_ACCOUNT_TYPE = "CHECKING";


    // Starting at 1000000000000000 to enforce the 16 digit
    private static final AtomicLong counter = new AtomicLong(1000000000000000L);

    public static long nextId() {
        return counter.getAndIncrement();
    }

    public Account(){
        this("Account Holder", DEFAULT_ACCOUNT_TYPE, 0.0);
    }
    public Account(String accountName, String accountType, Double accountBalance){
        this.id = nextId();
        this.accountType = accountType;
        this.accHolderName = accountName;
        this.accountBalance = accountBalance;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account account)) return false;
        return Objects.equals(id, account.id) && Objects.equals(accHolderName, account.accHolderName) && Objects.equals(accountType, account.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accHolderName, accountType);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accHolderName='" + accHolderName + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
