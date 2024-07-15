package com.pluralsight.DAO;

import com.pluralsight.models.Transaction;
import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    Transaction getTransaction(int id);
    List<Transaction> getAllTransactions();
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int id);
}
