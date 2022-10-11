package com.shop.models;

import com.shop.enums.Role;
import com.shop.models.Address;
import com.shop.models.Person;
import com.shop.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private final String customerId;
    private double balance;
    private Integer totalNumberOfProducts;
    private List<Product> cartItems;

    public Customer(String customerId, String firstName, String lastName, long phoneNumber,
                    String email, Address address, double balance) {
        super(firstName, lastName, phoneNumber, email, address);
        this.customerId = customerId;
        this.balance = balance;
        this.totalNumberOfProducts = 0;
        this.cartItems = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Product> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getTotalNumberOfProducts() {
        return totalNumberOfProducts;
    }

    public void setTotalNumberOfProducts(Integer totalNumberOfProducts) {
        this.totalNumberOfProducts = totalNumberOfProducts;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + "\n" +
                this.getEmail() + "\n" + this.getPhoneNumber() + "\n" +
                this.getAddress();
    }

    public String toString2() {
        return this.getFirstName() + " " + this.getLastName() + " " +
                this.getEmail() + " " + this.getPhoneNumber() + " " +
                this.getAddress();
    }
}
