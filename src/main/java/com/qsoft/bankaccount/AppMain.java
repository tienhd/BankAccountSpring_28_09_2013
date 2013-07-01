package com.qsoft.bankaccount;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import com.qsoft.bankaccount.persistence.model.BankAccountEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:26 AM
 */
public class AppMain
{
    public static void main(String[] args)
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "application_context.xml");

        BankAccountEntity bankAccountEntity = new BankAccountEntity("123456789");
        BankAccountDAO bankAccountDAO = (BankAccountDAO) appContext.getBean("bankAccountDao");
        bankAccountDAO.createBankAccount("123456789");

    }
}
