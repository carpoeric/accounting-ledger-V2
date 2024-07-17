package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.DAO.TransactionTypeDAO;
import com.pluralsight.models.Transaction;
import com.pluralsight.models.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
@CrossOrigin
@EnableMethodSecurity
@RequestMapping("transactions")
public class LedgerController {
    private TransactionDAO transactionDao;
    private TransactionTypeDAO transactionTypeDao;


    @Autowired
    public LedgerController(TransactionDAO transactionDao, TransactionTypeDAO transactionTypeDao) {
        this.transactionDao = transactionDao;
        this.transactionTypeDao = transactionTypeDao;
    }






    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> displayAllTransactions() {
        return transactionDao.getAllTransactions();
    }


    @GetMapping("/deposits")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionType> getDeposits() {
        return transactionTypeDao.getAllTransactionTypes().stream()
                .filter(t -> t.getType().equals("Deposit"))
                .toList();

    }

    @GetMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionType> getPayments() {
        return transactionTypeDao.getAllTransactionTypes().stream()
                .filter(t -> t.getType().equals("Payment"))
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDeposit")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addDeposit(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionDao.addTransaction(transaction);
        return newTransaction;

    }





    // unsure about what reports are exactly

}

