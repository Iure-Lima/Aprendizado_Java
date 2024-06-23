package com.api.parkingcontrol.models;

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
        created_at = now;
        update_at = now;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    @Getter
    @Setter
    private UUID id;

    @Column(name = "DT_CREATED_AT")
    @Getter
    private final Date created_at;

    @Column(name = "DT_UPDATE_AT")
    @Getter
    @Setter
    private Date update_at;

}
