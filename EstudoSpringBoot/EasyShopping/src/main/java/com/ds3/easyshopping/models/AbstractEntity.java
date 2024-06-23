package com.ds3.easyshopping.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    AbstractEntity(){
        Date now = new Date();
        createAt = now;
        updateAt = now;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID")
    @Getter @Setter
    private UUID id;

    @Column(name = "DT_CREATED_AT")
    @Getter
    private final Date createAt;

    @Column(name = "DT_UPDATED_AT")
    @Getter @Setter
    private Date updateAt;
}
