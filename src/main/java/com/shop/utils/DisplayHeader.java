package com.shop.utils;

import java.time.LocalDate;

public class DisplayHeader {
        private static void displayHeader() {
        System.out.println("*******************************************************************");
        System.out.println("AVAILABLE PRODUCTS");
        System.out.println("DATE:" + LocalDate.now());
        System.out.println("*******************************************************************");
        System.out.println("\tPRODUCT ID\t\tQUANTITY\t\t\tPRICE\t\tPRODUCT");
        System.out.println("*******************************************************************");
    }
}
