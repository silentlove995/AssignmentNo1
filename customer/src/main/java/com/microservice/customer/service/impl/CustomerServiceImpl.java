package com.microservice.customer.service.impl;

import com.microservice.customer.entity.Customer;
import com.microservice.customer.model.CustomerModel;
import com.microservice.customer.model.ResponseData;
import com.microservice.customer.model.dto.CustomerDto;
import com.microservice.customer.repository.CustomerRepository;
import com.microservice.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseData findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()){
            return new ResponseData(404, "Customer ID: " + id + " not found", "");
        }
        return new ResponseData(200, "Success", CustomerDto.customerDto(customer.get()));
    }

    @Override
    public ResponseData findAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> dtos = new ArrayList<>();
        for(Customer item : customers) {
            dtos.add(CustomerDto.customerDto(item));
        }
        return new ResponseData(200, "Success", dtos);
    }

    @Override
    public ResponseData save(CustomerModel customerModel) {
        return null;
    }

    @Override
    public ResponseData update(CustomerModel customerModel, Long id) {
        return null;
    }

    @Override
    public ResponseData delete(Long id) {
        if (!customerRepository.findById(id).isPresent()){
            return new ResponseData(404, "Customer ID: " + id + " not found", "");
        }
        customerRepository.deleteById(id);
        return new ResponseData(200, "Delete customer successfully", "");
    }
}
