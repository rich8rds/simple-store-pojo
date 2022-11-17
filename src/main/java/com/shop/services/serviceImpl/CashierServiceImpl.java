package com.shop.services.serviceImpl;

import com.shop.comparator.CustomerQuantityComparator;
import com.shop.models.*;
import com.shop.services.CashierService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class CashierServiceImpl implements CashierService {
    private final Store store;
    public CashierServiceImpl(Store store) {
        this.store = store;
    }

    @Override
    public Boolean sellProducts(Boolean isPriorityQueue) {
        int queueIndex = 0;
        boolean isQueuePresent = !store.getCustomerQueue().isEmpty(); // check if queue is not empty

        if(isPriorityQueue) {
            List<Customer> customersPriorityList = new LinkedList<>(store.getCustomerQueue());
            customersPriorityList.sort(Comparator.comparing(Customer::getTotalNumberOfProducts));

            while (queueIndex < customersPriorityList.size())
                issueReceipt(Objects.requireNonNull(customersPriorityList.remove(queueIndex)));
        }
        else {
            while (queueIndex < store.getCustomerQueue().size())
                issueReceipt(Objects.requireNonNull(store.getCustomerQueue().poll()));
        }
//        store.getCustomerQueue().clear();
        return isQueuePresent;
    }


    private final Object lock = "lock";
    @Override
    synchronized public void issueReceipt(Customer customer) {
        synchronized (lock) {
            double total;
            HashMap<Product, Integer> customerCart = customer.getProductCart();
            System.out.println("\n\n*******************************************************************");
            System.out.println("BUY MORE RECEIPT");
            System.out.print(customer);
            System.out.println(LocalDate.now());
            System.out.println("*******************************************************************");
            System.out.println("SN\t\tPRODUCT ID\t\t\tPRICE\t\tQUANTITY\tPRODUCT");
            System.out.println("*******************************************************************");

            customerCart.keySet().forEach(p ->
                    System.out.println(" " + p.toStringForReceipt() + customerCart.get(p) + "\t\t\t" + p.getProductName()));

            total = customerCart.keySet().stream()
                    .mapToDouble(x -> x.getPrice() * customerCart.get(x))
                    .reduce(Double::sum).orElse(0.0);

            customerCart.clear();
            System.out.println("*******************************************************************");
            System.out.println("TOTAL: " + total);
            System.out.println("*******************************************************************");
            System.out.println();
        }

    }
}
