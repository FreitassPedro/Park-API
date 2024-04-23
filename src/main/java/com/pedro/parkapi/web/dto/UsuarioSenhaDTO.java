package com.pedro.parkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioSenhaDTO(
        String senhaAtual,
        @NotBlank @Size(min = 6) String novaSenha,
        @NotBlank @Size(min = 6) String confirmaSenha) {
}
