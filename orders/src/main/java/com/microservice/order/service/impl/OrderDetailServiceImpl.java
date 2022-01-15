package com.microservice.order.service.impl;

import com.microservice.order.entity.Order;
import com.microservice.order.entity.OrderDetail;
import com.microservice.order.model.OrderDetailModel;
import com.microservice.order.model.ResponseData;
import com.microservice.order.model.dto.OrderDetailDto;
import com.microservice.order.model.dto.OrderDto;
import com.microservice.order.repository.OrderDetailRepository;
import com.microservice.order.service.OrderDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseData findById(Long id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (!orderDetail.isPresent()){
            return new ResponseData(404, "OrderDetail ID: " + id + " not found", "");
        }
        return new ResponseData(200, "Success", OrderDetailDto.orderDetailDto(orderDetail.get()));
    }

    @Override
    public ResponseData findAll() {
        List<OrderDetail> OrderDetails = orderDetailRepository.findAll();
        List<OrderDetailDto> dtos = new ArrayList<>();
        for(OrderDetail item : OrderDetails) {
            dtos.add(OrderDetailDto.orderDetailDto(item));
        }
        return new ResponseData(200, "Success", dtos);
    }

    @Override
    public ResponseData save(OrderDetailModel orderDetailModel) {
        ModelMapper modelMapper = new ModelMapper();
        OrderDetail orderDetail = modelMapper.map(orderDetailModel, OrderDetail.class);
        orderDetail = orderDetailRepository.save(orderDetail);
        OrderDetailDto dto = OrderDetailDto.orderDetailDto(orderDetailRepository.save(orderDetail));
        return new ResponseData(200, "Add OrderDetail successfully", dto);
    }

    @Override
    public ResponseData update(OrderDetailModel orderDetailModel, Long id) {
        if (!orderDetailRepository.findById(id).isPresent()){
            return new ResponseData(404, "OrderDetail ID: " + id + " not found", "");
        }
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id)
                .map(item -> {
                    ModelMapper modelMapper = new ModelMapper();
                    Long orderDetailId = item.getId();
                    item = modelMapper.map(orderDetailModel, OrderDetail.class);
                    item.setId(orderDetailId);
                    return item;
                });
        orderDetail.ifPresent(value -> orderDetailRepository.save(value));
        return new ResponseData(200, "Update product successfully", orderDetail);
    }

    @Override
    public ResponseData delete(Long id) {
        if (!orderDetailRepository.findById(id).isPresent()){
            return new ResponseData(404, "OrderDetail ID: " + id + " not found", "");
        }
        orderDetailRepository.deleteById(id);
        return new ResponseData(200, "Success", "");
    }
}
