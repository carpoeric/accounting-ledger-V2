package com.pluralsight.DAO;

import com.pluralsight.models.Transactions;
import java.util.List;

public interface TransactionDAO {
    Transactions addTransaction(Transactions transactions);
    Transactions getTransaction(int id);
    List<Transactions> getAllTransactions();
    void updateTransaction(Transactions transactions);
    void deleteTransaction(int id);
}
