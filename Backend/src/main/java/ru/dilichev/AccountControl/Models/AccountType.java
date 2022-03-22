package ru.dilichev.AccountControl.Models;

public class AccountType {
    private long id;
    private String name;
    private double profitability_percent;
    private double profitability_fixed;
    private boolean debiting;
    private boolean accrual;
    private String period;

    public AccountType()
    {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProfitability_percent() {
        return profitability_percent;
    }

    public void setProfitability_percent(double profitability_percent) {
        this.profitability_percent = profitability_percent;
    }

    public double getProfitability_fixed() {
        return profitability_fixed;
    }

    public void setProfitability_fixed(double profitability_fixed) {
        this.profitability_fixed = profitability_fixed;
    }

    public boolean isDebiting() {
        return debiting;
    }

    public void setDebiting(boolean debiting) {
        this.debiting = debiting;
    }

    public boolean isAccrual() {
        return accrual;
    }

    public void setAccrual(boolean accrual) {
        this.accrual = accrual;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
