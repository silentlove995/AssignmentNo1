package com.microservice.customer.service.impl;

import com.microservice.customer.entity.Order;
import com.microservice.customer.model.OrderModel;
import com.microservice.customer.model.ResponseData;
import com.microservice.customer.model.dto.OrderDto;
import com.microservice.customer.repository.OrderRepository;
import com.microservice.customer.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ResponseData findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()){
            return new ResponseData(404, "Order ID: " + id + " not found", "");
        }
        return new ResponseData(200, "Success", OrderDto.orderDto(order.get()));
    }

    @Override
    public ResponseData findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> dtos = new ArrayList<>();
        for(Order item : orders) {
            dtos.add(OrderDto.orderDto(item));
        }
        return new ResponseData(200, "Success", dtos);
    }

    @Override
    public ResponseData save(OrderModel orderModel) {
        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderModel, Order.class);
        order = orderRepository.save(order);
        OrderDto dto = OrderDto.orderDto(orderRepository.save(order));
        return new ResponseData(200, "Add order successfully", dto);
    }

    @Override
    public ResponseData update(OrderModel orderModel, Long id) {
        if (!orderRepository.findById(id).isPresent()){
            return new ResponseData(404, "Order ID: " + id + " not found", "");
        }
        Optional<Order> order = orderRepository.findById(id)
                .map(item -> {
                    ModelMapper modelMapper = new ModelMapper();
                    Long orderId = item.getId();
                    item = modelMapper.map(orderModel, Order.class);
                    item.setId(orderId);
                    return item;
                });
        order.ifPresent(value -> orderRepository.save(value));
        return new ResponseData(200, "Update product successfully", order);
    }

    @Override
    public ResponseData delete(Long id) {
        if (!orderRepository.findById(id).isPresent()){
            return new ResponseData(404, "Order ID: " + id + " not found", "");
        }
        orderRepository.deleteById(id);
        return new ResponseData(200, "Delete order successfully", "");
    }
}
