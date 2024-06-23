package com.ds3.easyshopping.models.DTOS;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SalesDTO(
        @NotNull UUID idProduct,
        @NotNull Integer amount
) { }
