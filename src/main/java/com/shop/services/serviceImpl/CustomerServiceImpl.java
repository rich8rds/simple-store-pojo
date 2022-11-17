package com.shop.services.serviceImpl;

import com.shop.enums.ProductEnum;
import com.shop.exceptions.*;
import com.shop.models.Customer;
import com.shop.models.Product;
import com.shop.models.Store;
import com.shop.services.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public String addProductToCart(Product selectedProduct, int orderedQuantity) {
        int quantityInStock = selectedProduct.getQuantityInStock();
        int totalQuantityOfProductsInCart = customer.getTotalNumberOfProducts();
        ProductEnum productName = selectedProduct.getProductName();
        HashMap<Product, Integer> customerCart = customer.getProductCart();

        try {
            if (quantityInStock == 0) throw new OutOfStockException("OUT OF STOCK");
            else if (orderedQuantity > quantityInStock)
                throw new ProductQuantityException("YOU CAN ONLY ORDER " + quantityInStock + " ITEMS OF " + productName);
            else {
                customerCart.put(selectedProduct, orderedQuantity);
                customer.setTotalNumberOfProducts(totalQuantityOfProductsInCart + orderedQuantity);
                selectedProduct.setQuantityInStock(quantityInStock - orderedQuantity);
//                System.out.println("Quantity: " + selectedProduct.getQuantityInStock());

                return productName + "(" + orderedQuantity + ") ADDED TO CART";
            }
        }catch (OutOfStockException | InsufficientFundsException | ProductQuantityException e) {
            return e.getMessage();
        }

//        Optional.of(orderedQuantity).orElseThrow(new OutOfStockException("OUT OF STOCK"))
    }

    private final Object lock = "lock";
    @Override
    public String buyProduct() {
        synchronized (lock) {
            double totalPriceOfCartProducts;
            double customerBalance = customer.getBalance();
            HashMap<Product, Integer> customerCart = customer.getProductCart();

            try {
                if (customerCart.isEmpty()) throw new CartEmptyException("YOUR CART IS EMPTY!");
                //If not empty check for the customer account balance against the total price of orders
                totalPriceOfCartProducts = customerCart.keySet()
                        .stream()
                        .mapToDouble(p -> p.getPrice() * p.getQuantityInStock()).sum();

                if ((customerBalance < totalPriceOfCartProducts))
                    throw new InsufficientFundsException("INSUFFICIENT FUNDS");

//                else if (subtractSelectedProductQuantityFromStore()) return "CLEARED";
                else return "CLEARED";
//                    throw new CustomerQueueException("Error Found!");
            } catch (CartEmptyException | InsufficientFundsException | CustomerQueueException e) {
                return e.getMessage();
            }
        }
    }

    @Override
    public Boolean subtractSelectedProductQuantityFromStore() {
        boolean isCustomerCleared = false;
        HashMap<Product, Integer> customerCart = customer.getProductCart();
        for (Product product : customerCart.keySet()) {
            Integer customerProductQuantity = customerCart.get(product);
            int storeQuantity = product.getQuantityInStock();

            if(storeQuantity <= 0) return isCustomerCleared;
            product.setQuantityInStock(storeQuantity - customerProductQuantity);
            isCustomerCleared = true;
        }
        return isCustomerCleared;
    }

    public Customer getCustomer() {
        return customer;
    }
}
