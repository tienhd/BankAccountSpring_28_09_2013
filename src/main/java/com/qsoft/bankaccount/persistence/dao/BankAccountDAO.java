package com.qsoft.bankaccount.persistence.dao;

import com.qsoft.bankaccount.persistence.model.BankAccountEntity;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:01 AM
 */
public interface BankAccountDAO
{
    public BankAccountEntity findByAccountNumber(String accountNumber);

    public BankAccountEntity create(String accountNumber);

    public void update(String accountNumber, double balance, String log);

    public void deleteByAccountNumber(String accountNumber);
}
