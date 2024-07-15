package com.pluralsight;

import com.pluralsight.Configuration.DatabaseConfig;
import com.pluralsight.Controllers.CustomerController;
import com.pluralsight.DAO.CustomerDAO;
import com.pluralsight.DAO.MySql.MySqlTransactionDAO;
import com.pluralsight.DAO.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class AccountingLedgerApplication   {

	public static void main(String[] args) {
		SpringApplication.run(AccountingLedgerApplication.class, args);
	}



}
