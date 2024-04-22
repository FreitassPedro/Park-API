package com.pedro.parkapi.web.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreatedDto {
    private String username;
    private String password;
}
