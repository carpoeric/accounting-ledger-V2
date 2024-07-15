package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.*;

@RestController
@CrossOrigin
@RequestMapping("reports")

public class ReportsController {
    private TransactionDAO transactionDao;

    @Autowired
    public ReportsController(TransactionDAO transactionDao){
        this.transactionDao = transactionDao;
    }

    @GetMapping("/monthToDate")
    public List<Transaction> monthToDate(){
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getMonthValue() == today.getMonthValue())
                .toList();
    }

    @GetMapping("/previousMonths")
    public List<Transaction> getPreviousMonths(){
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getMonthValue() < today.getMonthValue())
                .toList();
    }

    @GetMapping("/yearToDate")
    public List<Transaction> yearToDate(){
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getYear() < today.getYear())
                .toList();
    }

    // add version of yearToDate with path variable for given year

    // we don't have a field for vendor in any of the models, perhaps we can add vendor into a new table, may result in moving these methods elsewhere.
//    public List<Transaction> searchByVendor(String Vendor){
//
//    }


}
