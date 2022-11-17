package com.shop.models;

import com.shop.enums.Category;
import com.shop.enums.ProductEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Product {
    private static Integer counter = 0;
    private Integer count;
    private String productId;
    private ProductEnum productName;
    private double price;
    private int quantityInStock;
    private Category category;

    public Product(Integer count, String productId, ProductEnum productName, double price, Category category) {
        this.count = count;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Product(String productId, ProductEnum productName, double price, int quantityInStock, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantityInStock = quantityInStock;
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

    public String toStringForReceipt() {
       return "\t\t"+productId + "\t\t\t\t\t" + price + "\t\t";
    }
}
