package com.pluralsight.models.authentication;

import com.pluralsight.models.Customer;

public class LoginResponseDto {
    private String token;
    private Customer customer;

    public LoginResponseDto() {}

    public LoginResponseDto(String token, Customer customer) {
        this.token = token;
        this.customer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}