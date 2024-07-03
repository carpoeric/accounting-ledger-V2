package com.pluralsight.DAO.MySql;

import com.pluralsight.DAO.TransactionTypeDAO;
import com.pluralsight.models.TransactionType;
<<<<<<< HEAD
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlTransactionTypeDAO implements TransactionTypeDAO {
    private final Connection connection;

    public MySqlTransactionTypeDAO(Connection connection) {
        this.connection = connection;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class MySqlTransactionTypeDAO extends MySQLDaoBase implements TransactionTypeDAO {
    private DataSource dataSource;
@Autowired
    public MySqlTransactionTypeDAO(DataSource dataSource) {
        super(dataSource);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
    }

    @Override
    public void addTransactionType(TransactionType transactionType) {
<<<<<<< HEAD
        String sql = "INSERT INTO TransactionTypes (type) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO TransactionTypes (type) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setString(1, transactionType.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TransactionType getTransactionType(int id) {
<<<<<<< HEAD
        String sql = "SELECT * FROM TransactionTypes WHERE transaction_type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM TransactionTypes WHERE transaction_type_id = ?";
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
    public List<TransactionType> getAllTransactionTypes() {
        List<TransactionType> transactionTypes = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM TransactionTypes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
=======
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM TransactionTypes";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            while (resultSet.next()) {
                transactionTypes.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionTypes;
    }

    @Override
    public void updateTransactionType(TransactionType transactionType) {
<<<<<<< HEAD
        String sql = "UPDATE TransactionTypes SET type = ? WHERE transaction_type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "UPDATE TransactionTypes SET type = ? WHERE transaction_type_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setString(1, transactionType.getType());
            statement.setInt(2, transactionType.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTransactionType(int id) {
<<<<<<< HEAD
        String sql = "DELETE FROM TransactionTypes WHERE transaction_type_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM TransactionTypes WHERE transaction_type_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TransactionType mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("transaction_type_id");
        String type = resultSet.getString("type");
        return new TransactionType(id, type);
    }
}