package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.AccountType;

import java.util.List;

public interface AccountTypeDAO {
    void addAccountType(AccountType accType);

    void deleteAccountType(AccountType accType);

    void updateAccountType(AccountType accType);

    List<AccountType> getAccountTypeByCondition(Long id, String name,
                                                Double profitability_percent_low, Double profitability_percent_high,
                                                Double profitability_fixed_low, Double profitability_fixed_high,
                                                Boolean debiting, Boolean accrual,
                                                String period, Boolean valid);
}
