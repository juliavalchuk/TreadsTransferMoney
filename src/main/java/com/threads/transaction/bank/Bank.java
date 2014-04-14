package com.threads.transaction.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by julia
 */
public class Bank {
    private Map<Integer,Account> accounts;


    public Bank()
    {
        // accounts = Collections.synchronizedMap( new Hashtable<Integer, Account>());
        accounts = new ConcurrentHashMap<Integer, Account>();
    }

    public void addAccount(Account acc)
    {
        accounts.put(acc.getId(), acc);
    }

    public void addAccount(int number, int money)
    {
        accounts.put(number, new Account(number, money));
    }

    public void addAccount(int number)
    {
        addAccount(number, 0);
    }

    public void addAccounts(Account[] accounts1){
        int length = accounts1.length;
        for (int i = 0; i < length; ++i){
            addAccount(accounts1[i]);
        }
    }

    public Account getAccount(int number){
        return accounts.get(number);
    }

    public void transfer(Account from, Account to, int money) throws InterruptedException {
        if(from.getId() > to.getId()){
            transfer(to, from, -money);
        } else{
            if(from.getLock().tryLock(500, TimeUnit.MILLISECONDS)){
                try{
                    if(to.getLock().tryLock(500, TimeUnit.MILLISECONDS)){
                        try{
                              if(isCanTransact(from, to, money))   {
                                  from.addMoney(-money);
                                  to.addMoney(money);
                              }   else{
                                  // can't do transaction
                              }
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
        }
    }

    public void transfer(Transaction transaction) throws InterruptedException {
        transfer(transaction.FromAcc, transaction.ToAcc, transaction.Money);
    }

    private boolean isCanTransact(Account accFrom, Account accTo, int money){
         return (accFrom.getMoney() - money > 0) && (accTo.getMoney() + money > 0);
    }

    public long accSum()
    {
        long sum = 0;
        for(Account acc : accounts.values())
        {
            sum += acc.getMoney();
        }

        return sum;
    }
}
