package com.shop.utils;

import com.shop.models.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsCSVReaderTest {
    ProductsCSVReader productsCSVReader = new ProductsCSVReader();
    String filename = "src//resources//productDataTest.csv";

    @Test
    void assertReadingFromFileIsNotEmpty() {
        assertFalse(productsCSVReader.readProductListFromFile(filename).isEmpty());
    }

    @Test
    @DisplayName("Show true if writing is successful and false if error found.")
    void assertTrueThatWritingToFileIsSuccessful() {
        assertTrue(productsCSVReader.writeToFile(filename));
    }

    @Test
    void assertTrueThatWritingUpdatesToFileIsSuccessful() {
        List<Product> products = productsCSVReader.readProductListFromFile(filename);
        assertTrue(productsCSVReader.writeNewProductUpdatesToFile(filename, products));
    }
}