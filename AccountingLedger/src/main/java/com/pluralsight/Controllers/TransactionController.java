package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/transactions")

public class TransactionController {
    private TransactionDAO transactionDAO;
    @Autowired
    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

@GetMapping  
    public List<Transaction> showAllTransactions() {

List<Transaction> transactions = transactionDAO.getAllTransactions();
        return transactions;
    }


}
