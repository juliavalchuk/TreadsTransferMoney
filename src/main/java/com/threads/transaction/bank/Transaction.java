package com.threads.transaction.bank;

/**
 * Created by julia
 */
public class Transaction {
    public Account FromAcc;
    public Account ToAcc;
    public int Money;

    public Transaction(){}

    public Transaction(Account fromAccountId,Account toAccountId, int money ){
        FromAcc = fromAccountId;
        ToAcc = toAccountId;
        Money = money;
    }
}
