package com.pluralsight;

import com.pluralsight.Configuration.DatabaseConfig;
import com.pluralsight.Controllers.TransactionController;
import com.pluralsight.DAO.MySql.MySqlTransactionDAO;
import com.pluralsight.DAO.TransactionDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class AccountingLedgerApplication  {
//	private TransactionController transactionController;
//	private TransactionDAO transactionDAO;
//	private DatabaseConfig databaseConfig;
//	public AccountingLedgerApplication(TransactionController transactionController) {
//		this.transactionController = transactionController;
//		this.transactionDAO =  new MySqlTransactionDAO(databaseConfig.dataSource());
//	}

	public static void main(String[] args) {
		SpringApplication.run(AccountingLedgerApplication.class, args);
	}



}
