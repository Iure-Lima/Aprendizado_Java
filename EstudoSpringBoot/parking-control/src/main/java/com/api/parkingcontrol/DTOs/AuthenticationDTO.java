package com.api.parkingcontrol.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(@NotBlank @NotNull String login, @NotBlank @NotNull String password) {
}
