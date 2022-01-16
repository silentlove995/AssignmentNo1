package com.microservice.customer.model.dto;

import com.microservice.customer.entity.Customer;
import com.microservice.customer.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String PhoneNumber;
    private String email;
    private String avatar;
    private String address;
    private String description;
    private Set<Order> orders;

    public static CustomerDto customerDto(Customer customer){
        CustomerDto tmp = new CustomerDto();
        tmp.setName(customer.getName());
        tmp.setPhoneNumber(customer.getPhoneNumber());
        tmp.setEmail(customer.getEmail());
        tmp.setAvatar(customer.getAvatar());
        tmp.setAddress(customer.getAddress());
        tmp.setDescription(customer.getDescription());
        tmp.setOrders(customer.getOrders());
        return tmp;
    }
}
