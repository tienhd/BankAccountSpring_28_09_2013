package com.qsoft.bankaccount.persistence.dao.impl;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import com.qsoft.bankaccount.persistence.model.BankAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:03 AM
 */

@Transactional
public class BankAccountDAOImpl implements BankAccountDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BankAccountEntity findBankAccount(String accountNumber)
    {
        Query query = entityManager.createQuery("select o from BankAccountEntity o where o.accountNumber = :qAccountNumber");
        query.setParameter("qAccountNumber",accountNumber);
        return (BankAccountEntity) query.getResultList().get(0);
    }

    @Override
    public void createBankAccount(String accountNumber)
    {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(accountNumber);
        entityManager.persist(bankAccountEntity);
        entityManager.flush();
    }

    @Override
    public void updateBankAccount(String accountNumber)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteBankAccount(String accountNumber)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public EntityManager getEntityManager()
    {
        return this.entityManager;
    }
}