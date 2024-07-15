package com.pluralsight.DAO;

import com.pluralsight.models.Customer;
import java.util.List;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    Customer getCustomer(int id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
