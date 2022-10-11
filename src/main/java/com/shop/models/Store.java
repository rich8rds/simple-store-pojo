package com.shop.models;

import com.shop.utils.ProductsCSVReader;
import com.shop.enums.Qualification;
import com.shop.enums.Role;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Store {
    private static Integer QUEUE_LIMIT = 4;
    private ProductsCSVReader productsCSVReader;
    private final String productCSVFilename = "src//resources//productData.csv";
    private List<Product> products;
    private List<Staff> staffList;
    private List<Applicant> applicants;
    private List<Customer> customers;
    private PriorityQueue<Customer> customerPriorityQueue;

    public Store() {
        this.productsCSVReader = new ProductsCSVReader();
        this.customerPriorityQueue = new PriorityQueue<>(Comparator.comparing(Customer::getTotalNumberOfProducts).reversed());
        readAllApplicants();
        readAllApplicants();
        readAllStaffList();
        readAllCustomers();
    }

    public static void setQueueLimit(Integer queueLimit) {
        QUEUE_LIMIT = queueLimit;
    }

    public static Integer getQueueLimit() {
        return QUEUE_LIMIT;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public List<Staff> getStaff() {
        return this.staffList;
    }

    public PriorityQueue<Customer> getCustomerPriorityQueue() {
        return customerPriorityQueue;
    }

    public void setCustomerPriorityQueue(PriorityQueue<Customer> customerPriorityQueue) {
        this.customerPriorityQueue = customerPriorityQueue;
    }

    public void readAllApplicants() {
        //APPLICANTS
        Address maxAddress = new Address("Oliver Saunders Street", "Idumota", "Lagos");
        Applicant applicant1 = new Applicant("AP093", "John", "Maxwell",
                234814098123L, "johnMaxwell@gmail.com", maxAddress, Qualification.OND, 3);

        Address sylviaAddress = new Address("Timbot Street", "Lokoja", "Kogi");
        Applicant applicant2 = new Applicant("AP094", "Reinhard", "Brent",
                234814098123L, "reinhard.Brent@gmail.com", sylviaAddress, Qualification.HND, 1);

        this.applicants = new ArrayList<>(List.of(applicant1, applicant2));
    }

    public List<Customer> getCustomers() {
        //CUSTOMERS
        return this.customers;
    }

    public void readAllCustomers() {
        Address address1 = new Address("Polly Street", "Ikeja", "Lagos");
        Customer customer1 = new Customer("CUST893", "John", "Larson",
                234814098123L, "larson@gmail.com", address1, 100);

        Address address2 = new Address("Makinde Street", "Ibadan", "Oyo");
        Customer customer2 = new Customer("CUST894", "Innocent", "Peters",
                234814098123L, "innocent.peters@gmail.com", address2, 500_000);

        Address address3 = new Address("Ohen Street", "Benin", "Edo");
        Customer customer3 = new Customer("CUST895", "Kola", "Lolade",
                234814098123L, "kolade.lola@gmail.com", address3, 1_000_000);

        Address address4 = new Address("Preacher Street", "Kansas", "Wisconsin");
        Customer customer4 = new Customer("CUST898", "Irene", "Adler",
                234814098123L, "adam.lola@gmail.com", address3, 500_000);


        Address address5 = new Address("Kadane Street", "Orlando", "NewCousin");
        Customer customer5 = new Customer("CUST899", "Justice", "League",
                234814091123L, "justiceLeague@gmail.com", address5, 50_000);

        this.customers = new ArrayList<>(List.of(customer1, customer2, customer3, customer4, customer5));
    }

    public void readAllStaffList() {
        Address staffAddress1 = new Address("Johnson Rick Area", "Lekki", "Lagos");
        Staff manager = new Staff("MAN001", "Korede", "Bello",
                23480184657L, "korede.bello@gmail.com", Role.MANAGER, staffAddress1);

        Address staffAddress2 = new Address("Shalom Area", "Lekki", "Lagos");
        Staff cashier = new Staff("CASH001", "Clark", "Kent",
                23484938266L, "korede.bello@gmail.com", Role.CASHIER, staffAddress2);

        this.staffList = new ArrayList<>(List.of(manager, cashier));
    }

    public boolean readProductsFromFile() {
        this.products = new ArrayList<>();
        this.products = productsCSVReader.readProductListFromFile(productCSVFilename);
        return !this.products.isEmpty();
    }

    public boolean writeProductUpdatesToFile() {
        return productsCSVReader.writeNewProductUpdatesToFile(productCSVFilename, products);
    }

    public boolean writeProductsToFile() {
        return productsCSVReader.writeToFile(productCSVFilename);
    }

    public List<Product> getProducts() { return products; }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setStoreProducts(ProductsCSVReader productsCSVReader) {
        this.productsCSVReader = productsCSVReader;
    }
}
