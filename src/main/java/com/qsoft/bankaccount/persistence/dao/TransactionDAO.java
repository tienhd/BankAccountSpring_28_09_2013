package com.qsoft.bankaccount.persistence.dao;

import com.qsoft.bankaccount.persistence.model.TransactionEntity;

import java.util.List;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:05 AM
 */
public interface TransactionDAO extends GenericDAO
{
    public List<TransactionEntity> findByAccountNumber(String accountNumber);

    public TransactionEntity findById(int id);

    public void create(String accountNumber, double amount, String log);

    public void update(int id, String accountNumber, double amount, String log);

    public void deleteAllByAccountNumber(String accountNumber);
}
