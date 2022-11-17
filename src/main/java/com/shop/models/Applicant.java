package com.shop.models;


import com.shop.enums.Qualification;
import com.shop.services.serviceImpl.ManagerServiceImpl;
import lombok.*;

@Getter
@Setter
@ToString
public class Applicant extends Person {
    private String id;
    private Qualification qualification;
    private Integer yearsOfExperience;
     private final ManagerServiceImpl managerService = new ManagerServiceImpl();

     @Builder
    public Applicant(String id, String firstName, String lastName, long phoneNumber,
                     String email, Address address, Qualification qualification, Integer yearsOfExperience) {
        super(firstName, lastName, phoneNumber, email, address);
        this.id = id;
        this.qualification = qualification;
        this.yearsOfExperience = yearsOfExperience;
    }



    public String apply() {
        return managerService.hireCashier(this);
    }
}
