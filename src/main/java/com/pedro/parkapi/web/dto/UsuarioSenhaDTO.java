package com.pedro.parkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSenhaDTO {
        @NotBlank @Size private String senhaAtual;
        @NotBlank @Size(min = 6) private String novaSenha;
        @NotBlank @Size(min = 6) private String confirmaSenha;
}
