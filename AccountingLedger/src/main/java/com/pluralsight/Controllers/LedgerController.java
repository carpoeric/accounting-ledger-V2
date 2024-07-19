package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.DAO.TransactionTypeDAO;
import com.pluralsight.models.Transactions;
import com.pluralsight.models.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@EnableMethodSecurity
@RequestMapping("/api/transactions")
public class LedgerController {

    private final TransactionDAO transactionDao;
    private final TransactionTypeDAO transactionTypeDao;

    @Autowired
    public LedgerController(TransactionDAO transactionDao, TransactionTypeDAO transactionTypeDao) {
        this.transactionDao = transactionDao;
        this.transactionTypeDao = transactionTypeDao;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transactions> displayAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    @GetMapping("/deposits")
    @ResponseStatus(HttpStatus.OK)
    public List<Transactions> getDeposits() {
        return transactionDao.getAllTransactions().stream()
                .filter(t -> t.getTransactionTypeID() == 1) // Assuming 1 is the ID for deposits
                .collect(Collectors.toList());
    }

    @GetMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    public List<Transactions> getPayments() {
        return transactionDao.getAllTransactions().stream()
                .filter(t -> t.getTransactionTypeID() == 2) // Assuming 2 is the ID for payments
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addDeposit")
    @ResponseStatus(HttpStatus.CREATED)
    public Transactions addDeposit(@RequestBody Transactions transactions) {
        transactions.setTransactionTypeID(1); // Ensure this is set correctly for deposits
        return transactionDao.addTransaction(transactions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addPayment")
    @ResponseStatus(HttpStatus.CREATED)
    public Transactions addPayment(@RequestBody Transactions transactions) {
        transactions.setTransactionTypeID(2); // Ensure this is set correctly for payments
        return transactionDao.addTransaction(transactions);
    }


}
