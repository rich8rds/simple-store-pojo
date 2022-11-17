package com.shop.services;

import com.shop.models.Product;

import java.util.List;

public interface CustomerService {
    List<Product> getAvailableProductsInStore();
    String addProductToCart(Product product, int quantity);
    String buyProduct();
    Boolean subtractSelectedProductQuantityFromStore();
}
