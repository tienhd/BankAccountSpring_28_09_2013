package com.qsoft.bankaccount.persistence.model;

import javax.persistence.*;

/**
 * User: tienhd
 * Date: 7/3/13
 * Time: 1:58 PM
 */
@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "transaction_id", sequenceName = "transaction_id", allocationSize = 1)
public class TransactionEntity
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id")
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description")
    private String log;

    @Column(name = "time_stamp")
    private long timeStamp;

    public TransactionEntity()
    {

    }

    public TransactionEntity(String accountNumber, double amount, String log, long timeStamp)
    {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.log = log;
        this.timeStamp = timeStamp;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getLog()
    {
        return log;
    }

    public long getId()
    {
        return id;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void setLog(String log)
    {
        this.log = log;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        TransactionEntity that = (TransactionEntity) o;

        if (Double.compare(that.amount, amount) != 0)
        {
            return false;
        }
        if (timeStamp != that.timeStamp)
        {
            return false;
        }
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
        {
            return false;
        }
        if (log != null ? !log.equals(that.log) : that.log != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return 0;
    }
}
