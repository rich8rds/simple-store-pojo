package com.shop.models;

import com.shop.utils.ProductsCSVReader;
import com.shop.enums.Qualification;
import com.shop.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.*;

@Data
@Getter
@Setter
public class Store {
    private ProductsCSVReader productsCSVReader;
    private final String productCSVFilename = "convenience-store//src//resources//productData.csv";
    private volatile List<Product> products;
    private List<Staff> staffList;
    private List<Applicant> applicants;
    private List<Customer> customers;
    private Queue<Customer> customerQueue;

    public Store() {
        this.productsCSVReader = new ProductsCSVReader();
        this.customerQueue = new LinkedList<>();
        readAllApplicants();
        readAllApplicants();
        readAllStaffList();
        readAllCustomers();
    }

    public void readAllApplicants() {
        //APPLICANTS
        Address maxAddress = new Address("Oliver Saunders Street", "Idumota", "Lagos");
        Applicant applicant1 = new Applicant("AP093", "John", "Maxwell",
                234814098123L, "johnMaxwell@gmail.com", maxAddress, Qualification.OND, 3);

        Address sylviaAddress = new Address("Timbot Street", "Lokoja", "Kogi");
        Applicant applicant2 = new Applicant("AP094", "Silva", "Brent",
                234814098123L, "reinhard.Brent@gmail.com", sylviaAddress, Qualification.HND, 1);

        this.applicants = new ArrayList<>(List.of(applicant1, applicant2));
    }

    public void readAllCustomers() {
        Address address1 = new Address("Polly Street", "Ikeja", "Lagos");
        Customer john = new Customer("CUST893", "John", "Larson",
                234814098123L, "larson@gmail.com", address1, 100);

        Address address2 = new Address("Makinde Street", "Ibadan", "Oyo");
        Customer innocent = new Customer("CUST894", "Innocent", "Peters",
                234818398179L, "innocent.peters@gmail.com", address2, 500_000);

        Address address3 = new Address("Ohen Street", "Benin", "Edo");
        Customer kola = new Customer("CUST895", "Kola", "Lolade",
                234814098123L, "kolade.lola@gmail.com", address3, 1_000_000);

        Address address4 = new Address("Preacher Street", "Kansas", "Wisconsin");
        Customer irene = new Customer("CUST898", "Irene", "Adler",
                234844098474L, "ireneAdler@gmail.com", address3, 500_000);


        Address address5 = new Address("Kadane Street", "Orlando", "NewCousin");
        Customer justice = new Customer("CUST899", "Justice", "League",
                23492489123L, "justiceLeague@gmail.com", address5, 500_000);

        this.customers = new ArrayList<>(List.of(john, innocent, kola, irene, justice));
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

    public void writeProductsToFile() {
        productsCSVReader.writeToFile(productCSVFilename);
    }
}
