package com.qsoft.bankaccount.bussiness.impl;

import com.qsoft.bankaccount.bussiness.BankAccountService;
import com.qsoft.bankaccount.persistence.dao.BankAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: tienhd
 * Date: 7/1/13
 * Time: 12:55 AM
 */
public class BankAccountServiceImpl implements BankAccountService
{
    @Autowired
    public BankAccountDAO bankAccountDAO;
}
