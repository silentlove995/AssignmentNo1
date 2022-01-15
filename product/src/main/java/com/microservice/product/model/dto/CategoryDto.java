package com.microservice.product.model.dto;

import com.microservice.product.entity.Category;
import com.microservice.product.entity.Product;
import com.microservice.product.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private BaseStatus status;
    private Set<Product> products;

    public static CategoryDto categoryDto(Category category){
        CategoryDto tmp = new CategoryDto();
        tmp.setId(category.getId());
        tmp.setName(category.getName());
        tmp.setStatus(category.getStatus());
        tmp.setProducts(category.getProducts());
        return tmp;
    }
}
