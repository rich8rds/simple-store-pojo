package com.shop.service.serviceImpl;

import com.shop.exceptions.CartEmptyException;
import com.shop.exceptions.InsufficientFundsException;
import com.shop.exceptions.OutOfStockException;
import com.shop.exceptions.ProductQuantityException;
import com.shop.models.Customer;
import com.shop.models.Product;
import com.shop.models.Store;
import com.shop.service.CustomerService;

import java.io.FileNotFoundException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final Customer customer;
    private final Store store;

    public CustomerServiceImpl(Customer customer, Store store) {
        this.customer = customer;
        this.store = store;
    }

    @Override
    public List<Product> getAvailableProductsInStore() {
        return store.getProducts();
    }

    @Override
    public String addProductToCart(Product product, int quantity) {
        try {
            if (product.getQuantityInStock() == 0) throw new OutOfStockException("OUT OF STOCK");
            else if (quantity > product.getQuantityInStock())
                throw new ProductQuantityException("You can only order " + product.getQuantityInStock() +
                        " items of " + product.getProductName());
            else {
                customer.getCartItems().add(new Product(product.getProductId(), product.getProductName(),
                        product.getPrice(), quantity, product.getCategory()));
                customer.setTotalNumberOfProducts(customer.getTotalNumberOfProducts() + quantity);
                return product.getProductName() + "(" + quantity + ") added to cart";
            }
        }catch (OutOfStockException | InsufficientFundsException | ProductQuantityException e) {
            return e.getMessage();
        }
    }

    @Override
    public String buyProduct() {
        try {
            if (customer.getCartItems().isEmpty()) throw new CartEmptyException("Your Cart is Empty!");
            double totalPrice = 0d;
            for (Product p : customer.getCartItems()) {
                totalPrice += p.getPrice() * p.getQuantityInStock();
            }
            if ((customer.getBalance() < totalPrice))
                throw new InsufficientFundsException("Insufficient Funds!");
            else if(store.getCustomerPriorityQueue().offer(customer)) return "Cleared";
            else return "Error found";
        }catch (CartEmptyException | InsufficientFundsException e) {
            return e.getMessage();
        }
    }
}
