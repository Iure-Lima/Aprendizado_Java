package com.api.parkingcontrol.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel extends AbstractEntity{


    @Column(nullable = false, unique = true,length = 10)
    @Getter
    @Setter
    private String parkingSpotNumber;

    @Column(nullable = false, unique = true,length = 8)
    @Getter
    @Setter
    private String licensePlateCar;

    @Column(nullable = false,length = 70)
    @Getter
    @Setter
    private String brandCar;

    @Column(nullable = false,length = 70)
    @Getter
    @Setter
    private String modelCar;

    @Column(nullable = false,length = 70)
    @Getter
    @Setter
    private String colorCar;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDateTime registracionDate;

    @Column(nullable = false,length = 130)
    @Getter
    @Setter
    private String responsibleName;

    @Column(nullable = false,length = 30)
    @Getter
    @Setter
    private String apartament;

    @Column(nullable = false,length = 130)
    @Getter
    @Setter
    private String block;


}
