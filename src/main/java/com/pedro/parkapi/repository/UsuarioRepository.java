package com.pedro.parkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedro.parkapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
