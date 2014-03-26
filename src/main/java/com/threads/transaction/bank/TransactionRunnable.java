package com.threads.transaction.bank;

/**
 * Created by Yuliia_Valchuk
 */
public class TransactionRunnable implements Runnable {
    Transaction[] transactions;
    Bank bank;

    public TransactionRunnable(Bank bank, Transaction[] transactions){
        this.transactions = transactions;
        this.bank = bank;
    }

    @Override
    public void run() {
        int n = transactions.length;
        for(int i = 0; i < n; ++i){
            try {
                bank.transfer(transactions[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
