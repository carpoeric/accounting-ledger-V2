package com.pluralsight.DAO.MySql;

import com.pluralsight.DAO.CustomerDAO;
import com.pluralsight.models.Customer;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class MySqlCustomerDAO implements CustomerDAO {
    private final Connection connection;

    public MySqlCustomerDAO(Connection connection) {
        this.connection = connection;
=======
@Component
public class MySqlCustomerDAO extends MySQLDaoBase implements CustomerDAO {
    private DataSource dataSource;
@Autowired
    public MySqlCustomerDAO(DataSource dataSource) {
super(dataSource);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
    }

    @Override
    public void addCustomer(Customer customer) {
<<<<<<< HEAD
        String sql = "INSERT INTO Customers (name, email, phone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO Customers (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomer(int id) {
<<<<<<< HEAD
        String sql = "SELECT * FROM Customers WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM Customers WHERE customer_id = ?";
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
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT * FROM Customers";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
=======
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM Customers";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            while (resultSet.next()) {
                customers.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void updateCustomer(Customer customer) {
<<<<<<< HEAD
        String sql = "UPDATE Customers SET name = ?, email = ?, phone = ? WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "UPDATE Customers SET name = ?, email = ?, phone = ? WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setInt(4, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int id) {
<<<<<<< HEAD
        String sql = "DELETE FROM Customers WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
=======

        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM Customers WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
>>>>>>> 1e841120e8280170ac2792aff3266143b53172f5
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("customer_id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        return new Customer(id, name, email, phone);
    }
}