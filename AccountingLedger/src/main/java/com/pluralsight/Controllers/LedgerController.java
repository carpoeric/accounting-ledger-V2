package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.DAO.TransactionTypeDAO;
import com.pluralsight.models.Transaction;
import com.pluralsight.models.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("transactions")
public class LedgerController {
    private TransactionDAO transactionDao;
    private TransactionTypeDAO transactionTypeDao;

    @Autowired
    public LedgerController(TransactionDAO transactionDao, TransactionTypeDAO transactionTypeDao){
        this.transactionDao = transactionDao;
        this.transactionTypeDao = transactionTypeDao;
    }


    @GetMapping
    public List<Transaction> displayAllTransactions(){
        return transactionDao.getAllTransactions();
    }



    @GetMapping("/deposits")
    public List<TransactionType> getDeposits(){
        return transactionTypeDao.getAllTransactionTypes().stream()
                .filter(t-> t.getType().equals("Deposit"))
                .toList();

    }

    @GetMapping("/payments")
    public List<TransactionType> getPayments(){
        return transactionTypeDao.getAllTransactionTypes().stream()
                .filter(t-> t.getType().equals("Payment"))
                .toList();
    }

  // unsure about what reports are exactly







}
