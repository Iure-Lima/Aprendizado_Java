package com.mongo.productswithmongo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTORequest(
        @NotBlank @NotNull String name,
        @NotNull int stock,
        @NotNull double price
) { }
