package com.shop.service;

import com.shop.exceptions.OutOfStockException;
import com.shop.models.Customer;
import com.shop.models.Product;

import java.util.List;

public interface CustomerService {
    List<Product> getAvailableProductsInStore();
    String addProductToCart(Product product, int quantity) throws OutOfStockException;
    String buyProduct();

}
