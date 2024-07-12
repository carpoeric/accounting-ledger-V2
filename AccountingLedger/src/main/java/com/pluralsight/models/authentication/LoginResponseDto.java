package com.pluralsight.models.authentication;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.pluralsight.models.Customer; // Import the Customer class

public class LoginResponseDto {
    private String token;
    private Customer customer;

    // Constructor with parameters for token and customer
    public LoginResponseDto(String token, Customer customer) {
        this.token = token;
        this.customer = customer;
    }

    // Getter and setter for token
    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getter and setter for customer
    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}