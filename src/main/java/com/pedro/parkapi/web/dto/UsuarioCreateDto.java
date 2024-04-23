package com.pedro.parkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record UsuarioCreateDto(
        @NotBlank @Email(message = "Invalid e-mail format") String username,
        @NotBlank @Size(min = 6) String password) {
}
