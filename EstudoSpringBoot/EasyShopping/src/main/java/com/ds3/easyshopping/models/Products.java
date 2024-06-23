package com.ds3.easyshopping.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_PRODUCTS")
public class Products extends AbstractEntity {

    @Column(name = "NM_PRODUCTS", nullable = false)
    @Getter @Setter
    private String name;

    @Column(name = "PRICE_PRODUCTS", nullable = false)
    @Getter @Setter
    private Double price;

    @Column(name = "STOCK_PRODUCTS", nullable = false)
    @Getter @Setter
    private Integer stock;

    @Override
    public String toString(){
        return "Products [id=" + getId() + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
    }

}
