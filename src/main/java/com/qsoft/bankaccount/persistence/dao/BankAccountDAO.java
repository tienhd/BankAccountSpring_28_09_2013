package com.qsoft.bankaccount.persistence.dao;

import com.qsoft.bankaccount.persistence.model.BankAccountEntity;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:01 AM
 */
public interface BankAccountDAO
{
    public BankAccountEntity findBankAccount(String accountNumber);
    public BankAccountEntity createBankAccount(String accountNumber);
    public void saveBankAccount(String accountNumber, double balance, String log);
    public void deleteBankAccount(String accountNumber);
}
