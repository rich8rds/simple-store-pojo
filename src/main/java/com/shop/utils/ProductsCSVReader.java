package com.shop.utils;

import com.shop.enums.Category;
import com.shop.enums.Products;
import com.shop.models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsCSVReader {
    public List<Product> readProductListFromFile(String filename) {
        List<Product> products = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            int count = 0;
            while((line = bufferedReader.readLine()) != null) {
                if(count > 0) {
                    String[] lineArr = line.split(",");
                    products.add(new Product(count, lineArr[0],
                            Products.valueOf(lineArr[1]),
                            Double.parseDouble(lineArr[2]),
                            lineArr[3].equals("OUT OF STOCK") ? 0 : Integer.parseInt(lineArr[3]),
                            Category.valueOf(lineArr[4]))
                    );
                }
                count++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

        public boolean writeToFile(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            String string = "PRODUCT ID" + "," + "PRODUCT NAME" + "," + "PRICE" + "," + "QUANTITY" + "," + "CATEGORY" + "\n";
            String r1 = "F01" + "," + Products.COOKIE + "," + 500d + "," + 0 + "," + Category.FOOD + "\n";
            String r2 = "F02" + "," + Products.BREAD + "," + 400d + "," + 6 + "," + Category.FOOD + "\n";
            String r3 = "F03" + "," + Products.CORN_FLAKES + "," + 900d + "," + 12 + "," + Category.FOOD + "\n";
            String r4 = "F04" + "," + Products.BUTTER + "," + 600d + "," + 30 + "," +Category.FOOD + "\n";

            String r5 = "F05" + "," + Products.CREAM + "," + 1500d + "," + 5 + "," + Category.ESSENTIALS + "\n";
            String r6 = "F06" + "," + Products.DEODORANT + "," + 1400d + "," + 2 + "," + Category.ESSENTIALS + "\n";
            String r7 = "F07" + "," + Products.SANITARY_PAD + "," + 1900d + "," + 50 + "," + Category.ESSENTIALS + "\n";
            String r8 = "F08" + "," + Products.SHAVING_STICK + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r9 = "F09" + "," + Products.TOOTHBRUSH + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r10 = "F10" + "," + Products.SOAP + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r11 = "F11" + "," + Products.TOWEL + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r12 = "F12" + "," + Products.FACIAL_TISSUE + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r13 = "F13" + "," + Products.WET_WIPE + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";

            String r14 = "F14" + "," + Products.COKE + "," + 250d + "," + 20 + "," + Category.DRINK + "\n";
            String r15 = "F15" + "," + Products.SPRITE + "," + 250d + "," + 6 + "," + Category.DRINK + "\n";
            String r16 = "F16" + "," + Products.BEER + "," + 450d + "," + 5 + "," + Category.DRINK + "\n";

            String r17 = "F17" + "," + Products.MALTINA + "," + 5400d + "," + 10 + "," + Category.DRINK + "\n";
            String r18 = "F18" + "," + Products.CWAY + "," + 2400d + "," + 50 + "," + Category.DRINK + "\n";
            String r19 = "F19" + "," + Products.TOOTHPASTE + "," + 600d + "," + 8 + "," + Category.ESSENTIALS + "\n";
            String r20 = "F20" + "," + Products.TOILET_ROLL + "," + 400d + "," + 12 + "," + Category.ESSENTIALS + "\n";

            List<String> items = new ArrayList<>(List.of(string, r1, r2, r3, r4,
                    r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20));
            for(String item  : items)
                writer.append(item);

            System.out.println("File successfully written to file");
        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean writeNewProductUpdatesToFile(String filename, List<Product> products) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            String string = "PRODUCT ID" + "," + "PRODUCT NAME" + "," + "PRICE" + "," + "QUANTITY" + "," + "CATEGORY" + "\n";
            writer.write(string);
            for(Product p : products) {
                String productStr = "";
                if(p.getQuantityInStock() == 0)
                productStr = p.getProductId() + "," + p.getProductName() + "," + p.getPrice() + ","
                        + "OUT OF STOCK" + "," + p.getCategory() + "\n";

               else productStr = p.getProductId() + "," + p.getProductName() + "," + p.getPrice() + ","
                        + p.getQuantityInStock() + "," + p.getCategory() + "\n";

                writer.append(productStr);
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
