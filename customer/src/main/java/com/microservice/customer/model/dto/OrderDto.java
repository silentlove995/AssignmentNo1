package com.microservice.customer.model.dto;

import com.microservice.customer.entity.Customer;
import com.microservice.customer.entity.Order;
import com.microservice.customer.entity.OrderDetail;
import com.microservice.customer.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private Double totalPrice;
    private BaseStatus status;
    private Customer customer;
    private Set<OrderDetail> orderDetails;

    public static OrderDto orderDto(Order order){
        OrderDto tmp = new OrderDto();
        //tmp.setCustomerId(order.getId());
//        tmp.setCustomerName();
//        tmp.setPhoneNumber();
//        tmp.setAddress();
        tmp.setTotalPrice(order.getTotalPrice());
        tmp.setStatus(order.getStatus());
        tmp.setCustomer(order.getCustomer());
        tmp.setOrderDetails(order.getOrderDetails());
        return tmp;
    }
}
