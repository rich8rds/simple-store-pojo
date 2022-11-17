package com.shop.orders;

import com.shop.models.Customer;
import com.shop.models.Product;
import com.shop.models.Store;
import com.shop.services.serviceImpl.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrders {
    private final static String RESPONSE  = "CLEARED";

    public static List<Customer> addAllCustomersToQueue(Store store) {
        List<Product> productList = store.getProducts();
        List<Customer> customers = store.getCustomers();
        List<Customer> checkedOutCustomers = new ArrayList<>();

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

        //CUSTOMERS IN LINE
        Customer innocent = customers.get(1);
        Customer kola = customers.get(2);
        Customer irene = customers.get(3);
        Customer justice = customers.get(4);

        CustomerServiceImpl customerServiceForInnocent = new CustomerServiceImpl(innocent, store);
        CustomerServiceImpl customerServiceForKola = new CustomerServiceImpl(kola, store);
        CustomerServiceImpl customerServiceForJustice = new CustomerServiceImpl(justice, store);
        CustomerServiceImpl customerServiceForJustice2 = new CustomerServiceImpl(justice, store);
        CustomerServiceImpl customerServiceForIrene = new CustomerServiceImpl(irene, store);

        customerServiceForInnocent.addProductToCart(toothbrush, 2);
        customerServiceForInnocent.addProductToCart(soap, 4);
        customerServiceForInnocent.addProductToCart(towel, 3);

        String storeProcessingFeedbackInnocent = customerServiceForInnocent.buyProduct();
        if (storeProcessingFeedbackInnocent.equals(RESPONSE)) checkedOutCustomers.add(innocent);
        else System.out.println(storeProcessingFeedbackInnocent);


        customerServiceForKola.addProductToCart(facialTissue, 1);
        customerServiceForKola.addProductToCart(wetWipes, 2);
        customerServiceForKola.addProductToCart(coke, 2);

        String storeProcessingFeedbackKola = customerServiceForKola.buyProduct();
        if (storeProcessingFeedbackKola.equals(RESPONSE)) checkedOutCustomers.add(kola);
        else System.out.println(storeProcessingFeedbackKola);


        System.err.println(customerServiceForJustice.addProductToCart(cway, 50));
        customerServiceForJustice.addProductToCart(toothpaste, 8);
        customerServiceForJustice.addProductToCart(toiletRoll, 3);

        System.err.println(customerServiceForJustice2.addProductToCart(sprite, 10));
        customerServiceForJustice2.addProductToCart(toothpaste, 8);
        customerServiceForJustice2.addProductToCart(toiletRoll, 6);

//        System.err.println(customerService.addProductToCart(productList.get(0), 10)); //Throws OutOfStockException

        String storeProcessingFeedback = customerServiceForJustice.buyProduct();
        if (storeProcessingFeedback.equals(RESPONSE)) checkedOutCustomers.add(justice);
        else System.out.println(storeProcessingFeedback);

        System.err.println(customerServiceForIrene.addProductToCart(cway, 20));
        customerServiceForIrene.addProductToCart(beer, 4);
        customerServiceForIrene.addProductToCart(maltina, 3);

        String storeProcessingFeedbackIrene = customerServiceForIrene.buyProduct();
        if (storeProcessingFeedbackIrene.equals(RESPONSE)) checkedOutCustomers.add(irene);
        else System.out.println(storeProcessingFeedbackIrene);

        return checkedOutCustomers;
    }

}
