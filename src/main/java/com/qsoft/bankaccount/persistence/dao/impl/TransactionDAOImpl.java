package com.qsoft.bankaccount.persistence.dao.impl;

import com.qsoft.bankaccount.persistence.dao.TransactionDAO;
import com.qsoft.bankaccount.persistence.model.TransactionEntity;

import java.util.List;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:05 AM
 */
public class TransactionDAOImpl implements TransactionDAO
{
    @Override
    public List<TransactionEntity> findAllByAccountNumber(String accountNumber)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TransactionEntity findById(int id)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(String accountNumber, double amount, String log)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(int id, String accountNumber, double amount, String log)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAllByAccountNumber(String accountNumber)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
