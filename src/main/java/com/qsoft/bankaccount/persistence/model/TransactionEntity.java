package com.qsoft.bankaccount.persistence.model;

import javax.persistence.*;

/**
 * User: tienhd
 * Date: 7/3/13
 * Time: 1:58 PM
 */
@Entity
@Table(name = "transaction")
@SequenceGenerator(name ="transaction_id",sequenceName = "transaction_id",allocationSize = 1)
public class TransactionEntity
{
    @GeneratedValue(strategy= GenerationType.SEQUENCE ,generator = "transaction_id")
    @Id
    @Column(name = "id")
    private long id;

    @Column (name = "account_number")
    private String accountNumber;

    @Column (name = "amount")
    private double amount;

    @Column (name = "description")
    private String log;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getLog() {
        return log;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
