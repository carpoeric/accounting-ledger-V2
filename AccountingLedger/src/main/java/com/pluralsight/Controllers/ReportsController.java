package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.*;

@RestController
@CrossOrigin
@EnableMethodSecurity
@RequestMapping("reports")

public class ReportsController {
    private TransactionDAO transactionDao;

    @Autowired
    public ReportsController(TransactionDAO transactionDao){
        this.transactionDao = transactionDao;
    }

    @GetMapping("/monthToDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> monthToDate(){
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getMonthValue() == today.getMonthValue())
                .toList();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/previousMonths")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getPreviousMonths(){
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getMonthValue() < today.getMonthValue())
                .toList();
    }

    @GetMapping("/yearToDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> yearToDate(){
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getYear() == today.getYear())
                .toList();
    }

    // add version of yearToDate with path variable for given year

    @GetMapping("/{year}")
    public List<Transaction> getByYear(@PathVariable int year){
        LocalDateTime today = LocalDateTime.now();
        List <Transaction> transactions = transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getYear() == year)
                .toList();
        return transactions;
    }

    // we don't have a field for vendor in any of the models, perhaps we can add vendor into a new table, may result in moving these methods elsewhere.
//    public List<Transaction> searchByVendor(String Vendor){
//
//    }


}
