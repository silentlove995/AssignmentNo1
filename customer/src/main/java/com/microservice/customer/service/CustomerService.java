package com.microservice.customer.service;

import com.microservice.customer.model.CustomerModel;
import com.microservice.customer.model.ResponseData;

public interface CustomerService {
    ResponseData findById(Long id);
    ResponseData findAll();
    ResponseData save(CustomerModel customerModel);
    ResponseData update(CustomerModel customerModel, Long id);
    ResponseData delete(Long id);
}
