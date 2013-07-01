package com.qsoft.bankaccount.test;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import com.qsoft.bankaccount.persistence.model.BankAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 1:30 AM
 */
@Transactional
public class BankAccountDAODummy implements BankAccountDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBankAccount(String accountNumber)
    {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(accountNumber);
        entityManager.persist(bankAccountEntity);
        entityManager.flush();
    }

    @Override
    public BankAccountEntity findBankAccount(String accountNumber)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
}
