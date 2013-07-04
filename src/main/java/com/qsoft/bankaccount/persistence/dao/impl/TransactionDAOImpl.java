package com.qsoft.bankaccount.persistence.dao.impl;

import com.qsoft.bankaccount.persistence.dao.TransactionDAO;
import com.qsoft.bankaccount.persistence.model.TransactionEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:05 AM
 */
public class TransactionDAOImpl implements TransactionDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionEntity> findAllByAccountNumber(String accountNumber)
    {
        Query query = entityManager.createQuery("select o from TransactionEntity o where o.accountNumber = :qAccountNumber");
        query.setParameter("qAccountNumber",accountNumber);
        return query.getResultList();
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
