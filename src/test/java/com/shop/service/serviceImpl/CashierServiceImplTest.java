package com.shop.service.serviceImpl;

import com.shop.Main;
import com.shop.models.Address;
import com.shop.models.Customer;
import com.shop.models.Product;
import com.shop.models.Store;
import com.shop.utils.ProductsCSVReader;
import com.shop.enums.Category;
import com.shop.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceImplTest {
    Store store;
    ProductsCSVReader productsCSVReader;
    CashierServiceImpl cashierService;

    String filename = "src//resources//productDataTest.csv";
    Customer customer;

    @BeforeEach
    void readCSVFromFile() {
        store = new Store();
        productsCSVReader = new ProductsCSVReader();
        cashierService = new CashierServiceImpl(store);
        List<Product> products = productsCSVReader.readProductListFromFile(filename);
        store.setProducts(products);

        Address address1 = new Address("Polly Street", "Ikeja", "Lagos");
        customer = new Customer("CUST893", "John", "Larson",
                234814098123L, "larson@gmail.com", address1, 2_000_000);

        Product product1 = products.get(2);
        Product product2 = products.get(3);
        Product product3 = products.get(4);

        customer.getCartItems().add(new Product(product1.getProductId(), product1.getProductName(), product1.getPrice(),
                10, Category.ESSENTIALS));
        customer.getCartItems().add(new Product(product2.getProductId(), product2.getProductName(), product2.getPrice(),
                10, Category.ESSENTIALS));
        customer.getCartItems().add(new Product(product3.getProductId(), product3.getProductName(), product3.getPrice(),
                10, Category.ESSENTIALS));
    }

    @Test
    @DisplayName("Checks the customer's account balance before issuing receipt.")
    void issueReceiptShouldReturnClearedIfCustomerAccountIsGreaterThanTotalPrice() {
        Store.setQueueLimit(1);
        assertEquals("Issued Receipts", cashierService.sellProducts(customer));
    }


}