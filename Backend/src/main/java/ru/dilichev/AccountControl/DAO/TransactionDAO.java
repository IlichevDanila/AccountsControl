package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.Models.Transaction;

import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction tran);

    void deleteTransaction(Transaction tran);

    void updateTransaction(Transaction tran);

    String SQLByAccount(String account_id);

    List<Transaction> getTransactionByAccount(String account_id);

    String SQLByCondition(Long id, String debit_account_id, String credit_account_id,
                          String tran_time_low, String tran_time_high,
                          Double amount_low, Double amount_high);

    List<Transaction> getTransactionByCondition(Long id, String debit_account_id, String credit_account_id,
                                                String tran_time_low, String tran_time_high,
                                                Double amount_low, Double amount_high);
}
