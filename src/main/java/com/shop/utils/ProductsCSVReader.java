package com.shop.utils;

import com.shop.enums.Category;
import com.shop.enums.ProductEnum;
import com.shop.models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsCSVReader {
    public List<Product> readProductListFromFile(String productCSVFilename) {
        List<Product> products = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(productCSVFilename))) {
            String line;
            int count = 0;
            while((line = bufferedReader.readLine()) != null) {
                if(count > 0) {
                    String[] lineArr = line.split(",");
                    products.add(new Product(count, lineArr[0],
                            ProductEnum.valueOf(lineArr[1]),
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

        public boolean writeToFile(String productCSVFilename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(productCSVFilename))) {
            String string = "PRODUCT ID" + "," + "PRODUCT NAME" + "," + "PRICE" + "," + "QUANTITY" + "," + "CATEGORY" + "\n";
            String r1 = "F01" + "," + ProductEnum.COOKIE + "," + 500d + "," + 0 + "," + Category.FOOD + "\n";
            String r2 = "F02" + "," + ProductEnum.BREAD + "," + 400d + "," + 6 + "," + Category.FOOD + "\n";
            String r3 = "F03" + "," + ProductEnum.CORN_FLAKES + "," + 900d + "," + 12 + "," + Category.FOOD + "\n";
            String r4 = "F04" + "," + ProductEnum.BUTTER + "," + 600d + "," + 30 + "," +Category.FOOD + "\n";

            String r5 = "F05" + "," + ProductEnum.CREAM + "," + 1500d + "," + 5 + "," + Category.ESSENTIALS + "\n";
            String r6 = "F06" + "," + ProductEnum.DEODORANT + "," + 1400d + "," + 2 + "," + Category.ESSENTIALS + "\n";
            String r7 = "F07" + "," + ProductEnum.SANITARY_PAD + "," + 1900d + "," + 50 + "," + Category.ESSENTIALS + "\n";
            String r8 = "F08" + "," + ProductEnum.SHAVING_STICK + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r9 = "F09" + "," + ProductEnum.TOOTHBRUSH + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r10 = "F10" + "," + ProductEnum.SOAP + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r11 = "F11" + "," + ProductEnum.TOWEL + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r12 = "F12" + "," + ProductEnum.FACIAL_TISSUE + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";
            String r13 = "F13" + "," + ProductEnum.WET_WIPE + "," + 1600d + "," + 10 + "," + Category.ESSENTIALS + "\n";

            String r14 = "F14" + "," + ProductEnum.COKE + "," + 250d + "," + 20 + "," + Category.DRINK + "\n";
            String r15 = "F15" + "," + ProductEnum.SPRITE + "," + 250d + "," + 6 + "," + Category.DRINK + "\n";
            String r16 = "F16" + "," + ProductEnum.BEER + "," + 450d + "," + 5 + "," + Category.DRINK + "\n";

            String r17 = "F17" + "," + ProductEnum.MALTINA + "," + 5400d + "," + 10 + "," + Category.DRINK + "\n";
            String r18 = "F18" + "," + ProductEnum.CWAY + "," + 2400d + "," + 50 + "," + Category.DRINK + "\n";
            String r19 = "F19" + "," + ProductEnum.TOOTHPASTE + "," + 600d + "," + 8 + "," + Category.ESSENTIALS + "\n";
            String r20 = "F20" + "," + ProductEnum.TOILET_ROLL + "," + 400d + "," + 12 + "," + Category.ESSENTIALS + "\n";

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

    public boolean writeNewProductUpdatesToFile(String productCSVFilename, List<Product> products) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(productCSVFilename))) {
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
