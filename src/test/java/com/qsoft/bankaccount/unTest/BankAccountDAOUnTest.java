package com.qsoft.bankaccount.unTest;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import com.qsoft.bankaccount.persistence.model.BankAccountEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 1:28 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/it_test_context.xml"})
public class BankAccountDAOUnTest
{
    @Autowired
    private BankAccountDAO bankAccountDAO;

    public static final String accountNumber = "1234567890";
    @Before
    public void setUp()
    {

    }

    @Test
    public void testFindAccountByAccountNumber()
    {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(accountNumber,0,10000);
        BankAccountEntity getBankAccount = bankAccountDAO.findBankAccount(accountNumber);
        assertEquals(bankAccountEntity,getBankAccount);
    }


    @Test
    public void openNewAccountThenPersistentToDB()
    {
        String accountNumber = "0123456789";
        BankAccountEntity createBankAccount = bankAccountDAO.createBankAccount(accountNumber);

        //get account back from db

        BankAccountEntity getBankAccount = bankAccountDAO.findBankAccount(accountNumber);
        assertEquals(getBankAccount,createBankAccount);
    }
}
