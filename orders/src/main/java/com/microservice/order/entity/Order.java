package com.microservice.order.entity;

import com.microservice.order.entity.base.BaseEntity;
import com.microservice.order.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
@Table(name = "orders")
public class Order extends BaseEntity {
    private Long customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private BaseStatus status;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails;

}
