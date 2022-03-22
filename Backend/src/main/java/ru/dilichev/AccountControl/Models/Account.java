package ru.dilichev.AccountControl.Models;

public class Account {
    private String id;
    private long client_id;
    private long type;
    private String status;
    private double balance;
    private String creating_time;
    private String response_account;
    private String loan_account;

    public Account()
    {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreating_time() {
        return creating_time;
    }

    public void setCreating_time(String creating_time) {
        this.creating_time = creating_time;
    }

    public String getResponse_account() {
        return response_account;
    }

    public void setResponse_account(String response_account) {
        this.response_account = response_account;
    }

    public String getLoan_account() {
        return loan_account;
    }

    public void setLoan_account(String loan_account) {
        this.loan_account = loan_account;
    }
}
