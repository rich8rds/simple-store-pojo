package com.shop.models;

import com.shop.enums.Category;
import com.shop.enums.Products;


public class Product {
    private static Integer counter = 0;
    private Integer count;
    private String productId;
    private Products productName;

    private double price;
    private int quantityInStock;
    private Category category;

    public Product(String productId, Products productName, double price, Integer quantityInStock, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.quantityInStock = quantityInStock;
        this.price = price;
        this.category = category;
        this.count = counter++;
    }

    public Product(Integer count, String productId, Products productName, double price, int quantityInStock, Category category) {
        this.count = count;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Products getProductName() {
        return productName;
    }

    public void setProductName(Products productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {


        return "\t\t"+productId +
                "\t\t\t" + (quantityInStock == 0 ? "Out Of Stock" : quantityInStock) +
                (quantityInStock == 0 ? "\t\t" + price : "\t\t\t\t\t" + price) +
                "\t\t" + productName +
                 "\n";
    }
}
