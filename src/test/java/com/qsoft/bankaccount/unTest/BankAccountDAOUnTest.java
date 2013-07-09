package com.qsoft.bankaccount.unTest;

import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import com.qsoft.bankaccount.persistence.model.BankAccountEntity;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 1:28 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/it_test_context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class BankAccountDAOUnTest
{
    @Autowired
    private BankAccountDAO bankAccountDAO;
    private Connection dbConnection;

    public static final String accountNumber = "1234567890";
    private static final String resourcePath = new File ("").getAbsolutePath() + "/src/test/resources";

    public static final String JDBC_DRIVER = org.postgresql.Driver.class.getName();
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/bank_account";
    public static final String JDBC_SEVER_LOCATION = "localhost";
    public static final String JDBC_DATABASE = "bank_account";
    public static final String JDBC_USERNAME = "admin";
    public static final String JDBC_PASSWORD = "09020510";

    // DBUnit setUp
    @Before
    public void cleanInsertData() throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        IDataSet dataSet = importData();
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
        DataSource initDS = dataSource();
        dbConnection = initDS.getConnection();
    }

    public IDataSet importData() throws Exception {
        String dataFile = resourcePath + "/data.xml";
        System.out.println(dataFile);
        return new FlatXmlDataSetBuilder().build(new FileInputStream(dataFile));
    }

    public DataSource dataSource() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setServerName(JDBC_SEVER_LOCATION);
        dataSource.setDatabaseName(JDBC_DATABASE);
        dataSource.setUser(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);
        return dataSource;
    }

    @Test
    public void testFindAccountByAccountNumber()
    {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(accountNumber,100,10000);
        BankAccountEntity getBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);
        assertEquals(bankAccountEntity,getBankAccount);
    }

    @Test
    public void testAccountFindNotFound()
    {
        String accountNumber = "0123456789";
        BankAccountEntity getBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);
        assertEquals(getBankAccount,null);
    }

    @Test
    public void testFindAccountById()
    {
        String accountNumber = "0123456789";
        BankAccountEntity createBankAccount = bankAccountDAO.create(accountNumber);
        long id = createBankAccount.getId();

        Object obj = bankAccountDAO.findById(BankAccountEntity.class,id);
        assertEquals(createBankAccount,obj);
    }

    @Test
    public void testCreateNewAccountThenSaveToDB()
    {
        String accountNumber = "0123456789";
        BankAccountEntity createBankAccount = bankAccountDAO.create(accountNumber);

        //get account back from db then check equals
        BankAccountEntity getBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);
        assertEquals(getBankAccount,createBankAccount);
    }

    @Test
    public void testDepositedMoneyThenSaveAccountAfterTransactionToDB()
    {
        BankAccountEntity getBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);

        double newBalance = 50 + getBankAccount.getBalance(); //150
        bankAccountDAO.update(accountNumber, newBalance, "Deposited 50");

        BankAccountEntity savedBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);
        assertEquals(savedBankAccount.getAccountNumber(),accountNumber);
        assertEquals(savedBankAccount.getBalance(),newBalance,0.001);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSaveAccountWithAccountNumberContainCharacterThrowException()
    {
        String accountNumber = "a123456789";
        bankAccountDAO.update("a123456789", 50, "Deposite 50");
        fail("Exeption expected");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSaveAccountWithAccountNumberLongerThan10Chars()
    {
        String accountNumber = "012345678999";
        bankAccountDAO.update(accountNumber, 50, "Deposite 50");
        fail("Exeption expected");
    }

    @Test
    public void testWithdrawMoneyThenSaveAccountAfterTransactionToDB()
    {
        BankAccountEntity getBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);

        double newBalance = getBankAccount.getBalance() - 50; //50
        bankAccountDAO.update(accountNumber, newBalance, "Deposited 50");

        BankAccountEntity savedBankAccount = bankAccountDAO.findByAccountNumber(accountNumber);
        assertEquals(savedBankAccount.getAccountNumber(),accountNumber);
        assertEquals(savedBankAccount.getBalance(),newBalance,0.001);
    }
}
