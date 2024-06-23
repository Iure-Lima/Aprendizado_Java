package com.ds3.easyshopping.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_SALES")
public class Sales extends AbstractEntity{

    @Column(name = "ID_PRODUCT", nullable = false)
    @Getter @Setter
    private UUID idProduct;

    @Column(name="AMOUNT", nullable = false)
    @Getter @Setter
    private Integer amount;

    @Column(name = "DT_SALE", nullable = false)
    @Getter @Setter
    private Date dateSale;

    @Column(name = "PRICE_SALE", nullable = false)
    @Getter @Setter
    private Double priceSale;
}
