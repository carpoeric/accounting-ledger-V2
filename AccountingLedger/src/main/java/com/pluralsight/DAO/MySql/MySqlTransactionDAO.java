package com.pluralsight.DAO.MySql;

import com.pluralsight.DAO.TransactionDAO;
import com.pluralsight.models.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlTransactionDAO extends MySqlDaoBase implements TransactionDAO {

    @Autowired
    public MySqlTransactionDAO(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Transactions addTransaction(Transactions transactions) {
        String sql = "INSERT INTO Transactions (customer_id, transaction_type_id, amount, transaction_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transactions.getCustomerId());
            statement.setInt(2, transactions.getTransactionTypeID());
            statement.setBigDecimal(3, transactions.getAmount());
            statement.setTimestamp(4, Timestamp.valueOf(transactions.getTransactionDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public Transactions getTransaction(int id) {
        String sql = "SELECT * FROM Transactions WHERE transaction_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
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
    public List<Transactions> getAllTransactions() {
        List<Transactions> transactionsList = new ArrayList<>();
        String sql = "SELECT * FROM Transactions";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                transactionsList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionsList;
    }

    @Override
    public void updateTransaction(Transactions transactions) {
        String sql = "UPDATE Transactions SET customer_id = ?, transaction_type_id = ?, amount = ?, transaction_date = ? WHERE transaction_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transactions.getCustomerId());
            statement.setInt(2, transactions.getTransactionTypeID());
            statement.setBigDecimal(3, transactions.getAmount());
            statement.setTimestamp(4, Timestamp.valueOf(transactions.getTransactionDate()));
            statement.setInt(5, transactions.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransaction(int id) {
        String sql = "DELETE FROM Transactions WHERE transaction_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Transactions mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("transaction_id");
        int customerId = resultSet.getInt("customer_id");
        int transactionTypeID = resultSet.getInt("transaction_type_id");
        BigDecimal amount = resultSet.getBigDecimal("amount");
        Timestamp transactionDate = resultSet.getTimestamp("transaction_date");
        LocalDateTime transactionDateTime = transactionDate.toLocalDateTime();
        return new Transactions(id, customerId, transactionTypeID, amount, transactionDateTime);
    }
}
