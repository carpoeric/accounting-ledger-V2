package com.pluralsight.Controllers;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportsController {
    private final TransactionDAO transactionDao;

    @Autowired
    public ReportsController(TransactionDAO transactionDao){
        this.transactionDao = transactionDao;
    }

    @GetMapping("/monthToDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Transactions> monthToDate() {
        System.out.println("monthToDate endpoint hit");
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startOfMonth = today.withDayOfMonth(1).with(LocalTime.MIN);

        return transactionDao.getAllTransactions().stream()
                .filter(t -> t.getTransactionDate().getYear() == today.getYear() &&
                        t.getTransactionDate().getMonthValue() == today.getMonthValue() &&
                        !t.getTransactionDate().isAfter(today))
                .collect(Collectors.toList());
    }

    @GetMapping("/previousMonth")
    @ResponseStatus(HttpStatus.OK)
    public List<Transactions> getPreviousMonth() {
        System.out.println("previousMonth endpoint hit");
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1).with(LocalTime.MIN);
        LocalDateTime endOfPreviousMonth = today.withDayOfMonth(1).minusDays(1).with(LocalTime.MAX);

        return transactionDao.getAllTransactions().stream()
                .filter(t -> !t.getTransactionDate().isBefore(startOfPreviousMonth) && !t.getTransactionDate().isAfter(endOfPreviousMonth))
                .collect(Collectors.toList());
    }

    @GetMapping("/yearToDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Transactions> yearToDate(){
        System.out.println("yearToDate endpoint hit");
        LocalDateTime today = LocalDateTime.now();
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getYear() == today.getYear())
                .collect(Collectors.toList());
    }

    @GetMapping("/{year}")
    public List<Transactions> getByYear(@PathVariable int year){
        System.out.println("getByYear endpoint hit with year: " + year);
        return transactionDao.getAllTransactions().stream()
                .filter(t-> t.getTransactionDate().getYear() == year)
                .collect(Collectors.toList());
    }
}
