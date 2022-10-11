package com.shop.service.serviceImpl;

import com.shop.exceptions.CartEmptyException;
import com.shop.exceptions.InsufficientFundsException;
import com.shop.exceptions.OutOfStockException;
import com.shop.exceptions.ProductQuantityException;
import com.shop.models.Address;
import com.shop.models.Customer;
import com.shop.models.Product;
import com.shop.models.Store;
import com.shop.utils.ProductsCSVReader;
import com.shop.enums.Category;
import com.shop.enums.Products;
import com.shop.service.serviceImpl.CustomerServiceImpl;
import com.shop.service.serviceImpl.CashierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    Store store;
    CashierServiceImpl staffService;
    ProductsCSVReader productsCSVReader;
    CustomerServiceImpl customerService;
    Customer customer;
    String filename = "src//resources//productDataTest.csv";

    @BeforeEach
    @DisplayName("Set the customerService and customer before each test")
    void setUp() {
        store = new Store();
        staffService = new CashierServiceImpl(store);
        productsCSVReader = new ProductsCSVReader();

        List<Product> products = productsCSVReader.readProductListFromFile(filename);
        store.setProducts(products);

        Address address1 = new Address("Polly Street", "Ikeja", "Lagos");
        customer = new Customer("CUST893", "John", "Larson",
                234814098123L, "larson@gmail.com", address1, 2_000_000);

        Product product1 = products.get(2);
        Product product2 = products.get(5);
        Product product3 = products.get(10);

        customer.getCartItems().add(new Product(product1.getProductId(), product1.getProductName(), product1.getPrice(),
                7, Category.ESSENTIALS));
        customer.getCartItems().add(new Product(product2.getProductId(), product2.getProductName(), product2.getPrice(),
                10, Category.ESSENTIALS));
        customer.getCartItems().add(new Product(product3.getProductId(), product3.getProductName(), product3.getPrice(),
                10, Category.ESSENTIALS));
        customerService = new CustomerServiceImpl(customer);
    }

    @Test
    void testToSeeIfCustomerAddedProductToCartSuccessfully() {
        Product wine = new Product("PROD388", Products.WINE, 120000d, 11, Category.ESSENTIALS);
        int quantity = 11;
        assertFalse(quantity > wine.getQuantityInStock(),wine.getProductName() + " Out of Stock");
        String output = (customerService.addProductToCart(wine, quantity));
        assertEquals(wine.getProductName()+"("+ quantity +") added to cart", output);
    }

    @Test
    @DisplayName("Should return that only the numbers of items can be ordered and not more than quantity in store.")
    void testForWhenQuantityIsMoreThanStoreQuantity() {
        Product wine = new Product("PROD388", Products.WINE, 120000d, 11, Category.ESSENTIALS);
        int quantity = 12;
        String output = (customerService.addProductToCart(wine, quantity));
        assertEquals("You can only order " + wine.getQuantityInStock() + " items of " + wine.getProductName(), output);
    }

    @Test
    @DisplayName("Show Cleared when sufficient funds are available and Insufficient Funds! when not enough")
    void testForWhenSufficientFundsAreAvailableInCustomerBalance() {
        String result = customerService.buyProduct();
        System.out.println("Result: " + result);
        assertEquals("Cleared", result);
        assertNotEquals("Insufficient Funds!", result);
    }

    @Test
    @DisplayName("Throw EmptyCartException if customer product cart is EMPTY")
    void throwEmptyCartException() {
        customer.getCartItems().clear();
        String result = customerService.buyProduct();
        assertEquals("Your Cart is Empty!",result);
//        assertThrows(CartEmptyException.class, () -> customerService.buyProduct(), "Cart is Empty!");
    }

   @Test
    @DisplayName("Throw InsufficientFundsException if customer balance is less than total price.")
    void throwInsufficientFundsException() {
        customer.setBalance(200d);
       String result = customerService.buyProduct();
       assertEquals("Insufficient Funds!", result);
//        assertThrows(CartEmptyException.class, () -> customerService.buyProduct(), "Insufficient funds!");
    }
}