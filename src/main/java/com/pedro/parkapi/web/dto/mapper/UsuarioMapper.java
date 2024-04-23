package com.pedro.parkapi.web.dto.mapper;

import com.pedro.parkapi.entity.Usuario;
import com.pedro.parkapi.web.dto.UsuarioCreateDto;
import com.pedro.parkapi.web.dto.UsuarioResponseDTO;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createdDto) {
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

    public static List<UsuarioResponseDTO> toListDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDTO(user))
                //Collect junta todos objetos coletado e cria outra lista
                .collect(Collectors.toList());


    }

}
