package com.pluralsight.DAO.MySql;
import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlTransactionDAO extends MySqlDaoBase implements TransactionDAO {

    @Autowired
    public MySqlTransactionDAO(DataSource dataSource) {
        super(dataSource);

    }


    @Override
    public void addTransaction(Transaction transaction) {

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO Transactions (customer_id, amount, transaction_date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
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

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM Transactions";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
        try (Connection connection = getConnection()) {
            String sql = "UPDATE Transactions SET customer_id = ?, amount = ?, transaction_date = ? WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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

        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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