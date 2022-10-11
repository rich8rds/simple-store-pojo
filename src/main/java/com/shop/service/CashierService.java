package com.shop.service;

import com.shop.models.Customer;

public interface CashierService {
   String sellProducts(Customer customer);
    String issueReceipt(Customer customer);
}
