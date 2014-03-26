package com.threads.transaction.bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by julia
 */
public class Account {
    private final int id;
    private int money;
    private Lock lock;

    public Account(int id) {
        this(id, 0);
    }

    public Account(int id, int money)
    {
        this.id = id;
        this.money = money;
        lock = new ReentrantLock();
    }

    public void setMoney(int value)
    {
        money = value;
    }

    public void addMoney(int value)
    {
        money += value;
    }

    public int getMoney()
    {
        return money;
    }

    public int getId()
    {
        return id;
    }

    public Lock getLock(){
        return lock;
    }
}
