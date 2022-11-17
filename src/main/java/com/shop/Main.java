package com.shop;

import com.shop.enums.Qualification;
import com.shop.models.*;
import com.shop.services.serviceImpl.CashierServiceImpl;
import com.shop.services.serviceImpl.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private final static String RESPONSE  = "CLEARED";

    public static void main(String[] args) {
        Store store = new Store();

        Address maxAddress = new Address("Oliver Saunders Street", "Idumota", "Lagos");
        Applicant applicantJohn = new Applicant("AP093", "John", "Maxwell",
                234814098123L, "johnMaxwell@gmail.com", maxAddress, Qualification.OND, 3);
        System.err.println(applicantJohn.apply());


        // REGISTERED CUSTOMERS
        Address johnAddress = new Address("Polly Street", "Ikeja", "Lagos");
        Customer john = new Customer("CUST893", "John", "Larson",
                234814098123L, "larson@gmail.com", johnAddress, 100);

        Address innocentAddress = new Address("Makinde Street", "Ibadan", "Oyo");
        Customer innocent = new Customer("CUST894", "Innocent", "Peters",
                234818398179L, "innocent.peters@gmail.com", innocentAddress, 500_000);

        Address kolaAddress = new Address("Ohen Street", "Benin", "Edo");
        Customer kola = new Customer("CUST895", "Kola", "Lolade",
                234814098123L, "kolade.lola@gmail.com", kolaAddress, 1_000_000);

        Address ireneAddress = new Address("Preacher Street", "Kansas", "Wisconsin");
        Customer irene = new Customer("CUST898", "Irene", "Adler",
                234844098474L, "ireneAdler@gmail.com", kolaAddress, 500_000);

        Address justiceAddress = new Address("Kadane Street", "Orlando", "NewCousin");
        Customer justice = new Customer("CUST899", "Justice", "League",
                23492489123L, "justiceLeague@gmail.com", justiceAddress, 500_000);

        if(store.readProductsFromFile()){
            CustomerServiceImpl customerServiceForInnocent = new CustomerServiceImpl(innocent, store);
            CustomerServiceImpl customerServiceForKola = new CustomerServiceImpl(kola, store);
            CustomerServiceImpl customerServiceForJustice = new CustomerServiceImpl(justice, store);
            CustomerServiceImpl customerServiceForIrene = new CustomerServiceImpl(irene, store);

            List<Product> productList = store.getProducts();

            //PRODUCTS IN STORE
            Product toothbrush = productList.get(8);
            Product soap = productList.get(9);
            Product towel = productList.get(10);

            Product facialTissue = productList.get(11);
            Product wetWipes = productList.get(12);
            Product coke = productList.get(13);

            Product sprite = productList.get(14);
            Product beer = productList.get(15);
            Product maltina = productList.get(16);

            Product cway = productList.get(17);
            Product toothpaste = productList.get(18);
            Product toiletRoll = productList.get(19);


            //INNOCENT'S ORDERS
            customerServiceForInnocent.addProductToCart(toothbrush, 2);
            customerServiceForInnocent.addProductToCart(soap, 4);
            customerServiceForInnocent.addProductToCart(towel, 3);

            //KOLA'S ORDERS
            customerServiceForKola.addProductToCart(facialTissue, 1);
            customerServiceForKola.addProductToCart(wetWipes, 2);
            customerServiceForKola.addProductToCart(coke, 2);

            //JUSTICE'S ORDERS
            System.err.println(customerServiceForJustice.addProductToCart(cway, 50));
            customerServiceForJustice.addProductToCart(toothpaste, 8);
            customerServiceForJustice.addProductToCart(toiletRoll, 3);
            customerServiceForJustice.addProductToCart(sprite, 3);

            //IRENE'S ORDERS
            System.err.println(customerServiceForIrene.addProductToCart(cway, 20));
            customerServiceForIrene.addProductToCart(beer, 4);
            customerServiceForIrene.addProductToCart(maltina, 3);

            //ADD ALL CUSTOMERS TO A LIST
            List<CustomerServiceImpl> customerServiceList = new ArrayList<>(List.of(
                    customerServiceForIrene,
                    customerServiceForInnocent,
                    customerServiceForKola,
                    customerServiceForJustice));

            //EXECUTE ALL CUSTOMER ORDERS IN A THREAD
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            customerServiceList.forEach(customerService -> executorService.submit(() -> {
                CashierServiceImpl cashierService = new CashierServiceImpl(store);
                if (customerService.buyProduct().equals("CLEARED"))
                    cashierService.issueReceipt(customerService.getCustomer());
            }));
            executorService.shutdown();

            System.out.println(executorService.isShutdown());

        }else System.out.println("Error reading from file!");
    }
}
