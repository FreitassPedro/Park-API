package com.pedro.parkapi.web.dto.mapper;

import com.pedro.parkapi.entity.Usuario;
import com.pedro.parkapi.web.dto.UsuarioCreatedDto;
import com.pedro.parkapi.web.dto.UsuarioResponseDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreatedDto createdDto) {
        return new ModelMapper().map(createdDto, Usuario.class);
    }

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDTO> props = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }
}
