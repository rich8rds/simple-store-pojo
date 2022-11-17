package com.shop.models;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class Person {
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private Address address;
}
