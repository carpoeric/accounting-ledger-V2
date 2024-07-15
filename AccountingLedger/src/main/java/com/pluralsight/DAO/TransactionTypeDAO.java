package com.pluralsight.DAO;

import com.pluralsight.models.TransactionType;
import java.util.List;

public interface TransactionTypeDAO {
    void addTransactionType(TransactionType transactionType);
    TransactionType getTransactionType(int id);
    List<TransactionType> getAllTransactionTypes();
    void updateTransactionType(TransactionType transactionType);
    void deleteTransactionType(int id);
}