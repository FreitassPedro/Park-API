package com.pedro.parkapi.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class ClienteCreateDto {
    @NotBlank @Size(min = 5, max = 1000)
    private String nome;
    @NotBlank @Size(min = 11, max = 11)
    @CPF
    private String cpf;
}
