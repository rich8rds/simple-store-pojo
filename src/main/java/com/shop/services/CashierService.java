package com.shop.services;

import com.shop.models.Customer;

public interface CashierService {
    Boolean sellProducts(Boolean isPriorityQueue);
    void issueReceipt(Customer customer);
}
