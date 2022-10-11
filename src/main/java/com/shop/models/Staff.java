package com.shop.models;


import com.shop.enums.Role;
import com.shop.models.Address;
import com.shop.models.Person;

import java.time.LocalDate;
import java.util.List;

public class Staff extends Person {
    private String staffId;
    private Role role;

   public Staff(String staffId, String firstName, String lastName,
                long phoneNumber, String email, Role role, Address address) {
        super(firstName, lastName, phoneNumber, email, address);
        this.staffId = staffId;
        this.role = role;
   }

}
