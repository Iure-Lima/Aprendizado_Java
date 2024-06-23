package com.api.parkingcontrol.DTOs;

import com.api.parkingcontrol.models.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotBlank @NotNull String login, @NotBlank @NotNull String password, @NotNull UserRole role) {
}
