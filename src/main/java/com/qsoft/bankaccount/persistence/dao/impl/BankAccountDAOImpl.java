package com.qsoft.bankaccount.persistence.dao.impl;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import com.qsoft.bankaccount.persistence.model.BankAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:03 AM
 */

@Transactional
public class BankAccountDAOImpl extends GenericDAOImpl implements BankAccountDAO
{
//    @PersistenceContext
//    EntityManager entityManager;

    @Override
    public BankAccountEntity findByAccountNumber(String accountNumber)
    {
        Query query = entityManager.createQuery("select o from BankAccountEntity o where o.accountNumber = :qAccountNumber");
        query.setParameter("qAccountNumber", accountNumber);
        if (query.getResultList().size() > 0)
        {
            return (BankAccountEntity) query.getResultList().get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public BankAccountEntity create(String accountNumber)
    {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(accountNumber);
        entityManager.persist(bankAccountEntity);
        return bankAccountEntity;
    }

    @Override
    public void update(String accountNumber, double balance, String log)
    {
        for (Character c : accountNumber.toCharArray())
        {
            if ((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')))
            {
                throw new IllegalArgumentException();
            }
        }
        if (accountNumber.length() > 10)
        {
            throw new IllegalArgumentException();
        }
        BankAccountEntity bankAccountEntity = findByAccountNumber(accountNumber);
        bankAccountEntity.setBalance(balance);
        bankAccountEntity.setLog(log);
        entityManager.persist(bankAccountEntity);
    }

    @Override
    public void deleteByAccountNumber(String accountNumber)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public EntityManager getEntityManager()
    {
        return this.entityManager;
    }
}