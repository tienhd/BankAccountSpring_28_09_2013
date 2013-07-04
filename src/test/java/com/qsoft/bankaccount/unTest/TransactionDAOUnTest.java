package com.qsoft.bankaccount.unTest;

import com.qsoft.bankaccount.persistence.dao.TransactionDAO;
import com.qsoft.bankaccount.persistence.model.TransactionEntity;
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

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;

/**
 * User: tienhd
 * Date: 7/4/13
 * Time: 1:35 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/it_test_context.xml"})
public class TransactionDAOUnTest
{
    @Autowired
    private TransactionDAO transactionDAO;
    private Connection dbConnection;

    public static final String accountNumber = "1234567890";
    private static final String resourcePath = new File("").getAbsolutePath() + "/src/test/resources";

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
        String dataFile = resourcePath + "/data2.xml";
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
    public void testFindAllTransactionByAccountNumber()
    {
        List<TransactionEntity> resultTransactionEntityList = transactionDAO.findAllByAccountNumber(accountNumber);
        List<TransactionEntity> transactionEntityList = new ArrayList<TransactionEntity>();

        TransactionEntity transactionEntity1 = new TransactionEntity(accountNumber,20f,"Deposited to Account",12000);
        TransactionEntity transactionEntity2 = new TransactionEntity(accountNumber,-10f,"Withdraw from Account",15000);
        transactionEntityList.add(transactionEntity1);
        transactionEntityList.add(transactionEntity2);
        int i =0;
        for (TransactionEntity ti : resultTransactionEntityList )
        {
            assertEquals(ti,transactionEntityList.get(i));
            i++;
        }

    }
}
