package com.shop.service.serviceImpl;

import com.shop.exceptions.InsufficientFundsException;
import com.shop.models.*;
import com.shop.service.CashierService;

import java.time.LocalDate;
import java.util.Objects;

public class CashierServiceImpl implements CashierService {
    private final Store store;

    public CashierServiceImpl(Store store) {
        this.store = store;
    }

    @Override
    public String sellProducts(Customer customer) {
        int count = 0;
        for(Product product : customer.getCartItems()) {
            for (Product storeProduct : store.getProducts()) {
                if(storeProduct.getProductName().equals(product.getProductName())){
                    storeProduct.setQuantityInStock(storeProduct.getQuantityInStock() - product.getQuantityInStock());
                }
            }
        }
//        if(store.getCustomerPriorityQueue().size() == Store.getQueueLimit()) {
//            while(count < Store.getQueueLimit()) {
                issueReceipt(Objects.requireNonNull(store.getCustomerPriorityQueue().poll()));
//                count++;
//            }
            return "Issued Receipts";
//        }
//        return "Cleared";
    }


    @Override
    public String issueReceipt(Customer customer) {
        int count = 0;
        double total = 0;
        System.out.println("\n\n*******************************************************************");
        System.out.println("BUY MORE RECEIPT");
        System.out.println(customer.toString2());
        System.out.println(LocalDate.now());
        System.out.println("*******************************************************************");
        System.out.println("SN\t\tPRODUCT ID\tQUANTITY\t\t\tPRICE\t\tPRODUCT");
        System.out.println("*******************************************************************");

        for(Product p : customer.getCartItems()) {
            System.out.println((++count) +""+ p);
            total += p.getPrice() * p.getQuantityInStock();
        }
        System.out.println("*******************************************************************");
        System.out.println("TOTAL: " + total);
        System.out.println("*******************************************************************");
        System.out.println();

        return "Successful";
    }
}
