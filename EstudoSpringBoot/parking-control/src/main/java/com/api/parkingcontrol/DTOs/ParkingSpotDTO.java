package com.api.parkingcontrol.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ParkingSpotDTO(

        @NotBlank
        String parkingSpotNumber,

        @NotBlank
        @Size(max = 7)
        String licensePlateCar,

        @NotBlank
        String brandCar,

        @NotBlank
        String modelCar,

        @NotBlank
        String colorCar,

        @NotBlank
        String responsibleName,

        @NotBlank
        String apartament,

        @NotBlank
        String block
) { }
