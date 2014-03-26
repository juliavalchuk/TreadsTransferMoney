package com.threads.transaction.bank;

/**
 * Created by julia
 */
public class Main {

    public static void main(String... args) throws InterruptedException {
        int N = 100, n = 3000, m = 1000;

        Account[] accounts = createAccounts(N);
        Thread[] threads = new Thread[n];

        Bank bank = new Bank();
        bank.addAccounts(accounts);

        System.out.println("Bank sum before transaction: " + bank.accSum());
        System.out.println("First acc sum: " + bank.getAccount(0).getMoney());

        for(int i = 0; i < n; ++i){
            threads[i] = new Thread(new TransactionRunnable(bank, createTransactions(accounts, m))) ;
        }

        for(int i = 0; i < n; ++i){
            threads[i].start();
        }

        for(int i = 0; i < n; ++i){
            threads[i].join();
        }

        System.out.println("Bank sum after transaction: " + bank.accSum());
        System.out.println("First acc sum: " + bank.getAccount(0).getMoney());

    }

    public static Account[] createAccounts(int n){
        Account[] accounts = new Account[n];
        int maxValue = 10000000;

        for (int i = 0; i < n; ++i){
            accounts[i] = new Account(i, (int)(Math.random() * maxValue));
        }
        return accounts;
    }

    public static Transaction[] createTransactions(Account[] accounts, int numbTransact){
        Transaction[] transactions = new Transaction[numbTransact];
        Account fid, sid;
        int money;
        int maxMoneyT = 10000;
        int n = accounts.length;

        for (int i = 0; i < numbTransact; ++i){
            fid = accounts[(int)(Math.random() * n)];
            do{
                sid = accounts[(int)(Math.random() * n)];
            }while (fid == sid);
            money = (int)(Math.random() * maxMoneyT);
            transactions[i] = new Transaction(fid, sid, money);
        }
        return transactions;
    }
}
