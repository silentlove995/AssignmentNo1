package com.microservice.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.product.entity.base.BaseEntity;
import com.microservice.product.enums.BaseStatus;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class Product extends BaseEntity {

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "descriptions", columnDefinition = "TEXT")
    private String description;

    @Column(name = "thumbnails")
    private String thumbnail;

    @Column
    private Double price;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private BaseStatus status;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false, referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

    public Product(String productName, String description, String thumbnail, double price) {
        this.productName = productName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = BaseStatus.ACTIVE;
    }
}
