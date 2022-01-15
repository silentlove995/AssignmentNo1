package com.microservice.product.entity;

import com.microservice.product.entity.base.BaseEntity;
import com.microservice.product.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class Category extends BaseEntity {

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column
    private String type;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private BaseStatus status;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
