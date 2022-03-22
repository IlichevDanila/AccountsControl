package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account acc);

    void deleteAccount(Account acc);

    void updateAccount(Account acc);

    List<Account> getAccountByCondition(String id, String status, String typeName, Long clientId,
                                        String creating_time_low, String creating_time_high,
                                        String response_account, String loan_account,
                                        Double balance_low, Double balance_high);
}
