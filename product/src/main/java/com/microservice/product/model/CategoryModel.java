package com.microservice.product.model;

import com.microservice.product.entity.Product;
import com.microservice.product.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private Long id;
    private String name;
    private BaseStatus status;
    private Set<Product> products;

}
