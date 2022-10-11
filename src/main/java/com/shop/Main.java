package com.shop;

import com.shop.enums.Qualification;
import com.shop.exceptions.OutOfStockException;
import com.shop.models.Customer;
import com.shop.service.serviceImpl.CustomerServiceImpl;
import com.shop.models.Store;
import com.shop.models.*;
import com.shop.service.serviceImpl.CashierServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        System.out.println(store.writeProductsToFile());
        if(store.readProductsFromFile()) {
            displayHeader();
            System.out.println(store.getProducts());
            multipleCustomerOrders(store);

            System.out.println(store.getProducts());
            String result = store.writeProductUpdatesToFile() ? "Updates saved to file" : "Error writing to file";
            System.out.println("UPDATE: " + result);
        }else System.out.println("Error reading from database.");

        Address tomAddress = new Address("Rabbi Street", "Port Harcourt", "Rivers");
        Applicant applicant = new Applicant("AP095", "Thompson", "Leke",
                234814098123L, "kolade.lola@gmail.com", tomAddress, Qualification.HND, 5);

        System.out.println(applicant.apply());
    }

    private static void displayHeader() {
        System.out.println("*******************************************************************");
        System.out.println("AVAILABLE PRODUCTS");
        System.out.println("DATE:" + LocalDate.now());
        System.out.println("*******************************************************************");
        System.out.println("\tPRODUCT ID\t\tQUANTITY\t\t\tPRICE\t\tPRODUCT");
        System.out.println("*******************************************************************");
    }

    public static void multipleCustomerOrders(Store store) {
        CashierServiceImpl staffService = new CashierServiceImpl(store);
        List<Product> productList = store.getProducts();
        List<Customer> customers = store.getCustomers();

        Customer customer = customers.get(1);
        CustomerServiceImpl customerService = new CustomerServiceImpl(customer, store);

        try {
            System.out.println(customerService.addProductToCart(productList.get(8), 2));
            System.out.println(customerService.addProductToCart(productList.get(9), 4));
            System.out.println(customerService.addProductToCart(productList.get(10), 3));
//                System.err.println(customerService.addProductToCart(productList.get(0), 10)); //Throws OutOfStockException
        } catch (OutOfStockException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        if(customerService.buyProduct().equals("Cleared"))
            staffService.sellProducts(customer);
        else System.out.println("No purchases made");

        Customer customer2 = customers.get(2);
        CustomerServiceImpl customerService2 = new CustomerServiceImpl(customer2, store);

        try {
            System.out.println(customerService2.addProductToCart(productList.get(11), 1));
            System.out.println(customerService2.addProductToCart(productList.get(12), 2));
            System.out.println(customerService2.addProductToCart(productList.get(13), 2));
//                System.err.println(customerService.addProductToCart(productList.get(0), 10)); //Throws OutOfStockException
        } catch (OutOfStockException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        if(customerService2.buyProduct().equals("Cleared"))
            staffService.sellProducts(customer2);
        else System.out.println("No purchases made");

        Customer customer3 = customers.get(3);
        CustomerServiceImpl customerService3 = new CustomerServiceImpl(customer3, store);

        try {
            System.out.println(customerService3.addProductToCart(productList.get(14), 6));
            System.out.println(customerService3.addProductToCart(productList.get(15), 4));
            System.out.println(customerService3.addProductToCart(productList.get(16), 3));
//                System.err.println(customerService.addProductToCart(productList.get(0), 10)); //Throws OutOfStockException
        } catch (OutOfStockException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        if(customerService3.buyProduct().equals("Cleared"))
            staffService.sellProducts(customer3);
        else System.out.println("No purchases made");

        Customer customer4 = customers.get(4);
        CustomerServiceImpl customerService4 = new CustomerServiceImpl(customer4, store);

        System.out.println(customerService4.addProductToCart(productList.get(17), 8));
        System.out.println(customerService4.addProductToCart(productList.get(18), 8));
        System.out.println(customerService4.addProductToCart(productList.get(19), 3));
        System.err.println(customerService.addProductToCart(productList.get(0), 10)); //Throws OutOfStockException

        if(customerService4.buyProduct().equals("Cleared"))
            staffService.sellProducts(customer4);
        else System.out.println("No purchases made");

//        store.getCustomerPriorityQueue().forEach(x -> System.out.println(x.toString2()));
    }
}
