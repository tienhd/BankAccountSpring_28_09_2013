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
public class TransactionDAOImpl extends GenericDAOImpl implements TransactionDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionEntity> findByAccountNumber(String accountNumber)
    {
        Query query = entityManager.createQuery("select o from TransactionEntity o where o.accountNumber = :qAccountNumber");
        query.setParameter("qAccountNumber", accountNumber);
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

    @Override
    public List<TransactionEntity> findBetween2TimeStamp(String accountNumber, long startTime, long endTime)
    {
        Query query = entityManager.createQuery("select c from TransactionEntity c where c.timeStamp > :startTime " +
                "and c.timeStamp < :endTime and c.accountNumber = :accountNumber");
        query.setParameter("accountNumber", accountNumber);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        return query.getResultList();
    }
}
