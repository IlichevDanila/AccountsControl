package ru.dilichev.AccountControl.Models;

public class Transaction {
    private long id;
    private String debit_account_id;
    private String credit_account_id;
    private String tran_time;
    private double amount;

    public Transaction()
    {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDebit_account_id() {
        return debit_account_id;
    }

    public void setDebit_account_id(String debit_account_id) {
        this.debit_account_id = debit_account_id;
    }

    public String getCredit_account_id() {
        return credit_account_id;
    }

    public void setCredit_account_id(String credit_account_id) {
        this.credit_account_id = credit_account_id;
    }

    public String getTran_time() {
        return tran_time;
    }

    public void setTran_time(String tran_time) {
        this.tran_time = tran_time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
