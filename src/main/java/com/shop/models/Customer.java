package com.shop.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public class Customer extends Person {
    private final String customerId;
    private double balance;
    private Integer totalNumberOfProducts;
//    private List<Product> productCart;
    private LinkedHashMap<Product, Integer> productCart;

    @Builder
    public Customer(String customerId, String firstName, String lastName, long phoneNumber,
                    String email, Address address, double balance) {
        super(firstName, lastName, phoneNumber, email, address);
        this.customerId = customerId;
        this.balance = balance;
        this.totalNumberOfProducts = 0;
        this.productCart = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + " " + this.getEmail() + " "
                + this.getPhoneNumber() +  "   \n" + totalNumberOfProducts + " Products \n";
    }
       public String toStringNumber2() {
        return this.getFirstName() + " " + this.getLastName() + " " + this.getEmail() + " "
                + this.getPhoneNumber() +  " " + totalNumberOfProducts + " Products \n";
    }
}
