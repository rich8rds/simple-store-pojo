package com.shop.comparator;

import com.shop.models.Customer;

import java.util.Comparator;

public class CustomerQuantityComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getTotalNumberOfProducts().compareTo(o2.getTotalNumberOfProducts());
    }
}
