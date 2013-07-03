package com.qsoft.bankaccount.itTest;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:35 AM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/it_test_context.xml"})
public class BankAccountDAOItTest
{
    @Autowired
    private BankAccountDAO bankAccountDAO;

    @Test
    public void TestPersistToDB() {
        String accountNumber = "123456789";
        bankAccountDAO.create(accountNumber);
    }
}
