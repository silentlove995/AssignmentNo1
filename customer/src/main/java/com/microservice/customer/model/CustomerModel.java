package com.microservice.customer.model;

import com.microservice.customer.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private String name;
    private String PhoneNumber;
    private String email;
    private String avatar;
    private String address;
    private String description;
    private Set<Order> orders;
}
