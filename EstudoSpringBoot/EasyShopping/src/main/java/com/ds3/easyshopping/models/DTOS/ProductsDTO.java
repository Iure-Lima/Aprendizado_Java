package com.ds3.easyshopping.models.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductsDTO(
        @NotBlank String name,
        @NotNull Double price,
        @NotNull Integer stock
) { }
