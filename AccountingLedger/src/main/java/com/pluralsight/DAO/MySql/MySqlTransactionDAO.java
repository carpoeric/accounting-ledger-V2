package com.pluralsight.DAO.MySql;
import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transaction;
<<<<<<< HEAD

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class MySqlTransactionDAO implements TransactionDAO {
    private final Connection connection;

    public MySqlTransactionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transactions (customer_id, amount, transaction_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======
@Component
public class MySqlTransactionDAO extends MySQLDaoBase implements TransactionDAO {

    @Autowired
    public MySqlTransactionDAO(DataSource dataSource) {
        super(dataSource);

    }


    @Override
    public void addTransaction(Transaction transaction) {

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO Transactions (customer_id, amount, transaction_date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setInt(1, transaction.getCustomerId());
            statement.setBigDecimal(2, transaction.getAmount());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getTransactionDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getTransaction(int id) {
<<<<<<< HEAD
        String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM Transactions";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
=======
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM Transactions";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            while (resultSet.next()) {
                transactions.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public void updateTransaction(Transaction transaction) {
<<<<<<< HEAD
        String sql = "UPDATE Transactions SET customer_id = ?, amount = ?, transaction_date = ? WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======
        try (Connection connection = getConnection()) {
            String sql = "UPDATE Transactions SET customer_id = ?, amount = ?, transaction_date = ? WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setInt(1, transaction.getCustomerId());
            statement.setBigDecimal(2, transaction.getAmount());
            statement.setTimestamp(3, Timestamp.valueOf(transaction.getTransactionDate()));
            statement.setInt(4, transaction.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int id) {
<<<<<<< HEAD
        String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Transaction mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("transaction_id");
        int customerId = resultSet.getInt("customer_id");
        BigDecimal amount = resultSet.getBigDecimal("amount");
        Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
        return new Transaction(id, customerId, amount, transactionDate.toLocalDateTime());
    }
}