package com.microservice.order.model.dto;

import com.microservice.order.entity.Order;
import com.microservice.order.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private Order order;

    public static OrderDetailDto orderDetailDto(OrderDetail orderDetail){
        OrderDetailDto tmp = new OrderDetailDto();
        tmp.setProductId(orderDetail.getId());
        tmp.setQuantity(orderDetail.getQuantity());
        tmp.setUnitPrice(orderDetail.getUnitPrice());
        tmp.setOrder(orderDetail.getOrder());
        return tmp;
    }
}
