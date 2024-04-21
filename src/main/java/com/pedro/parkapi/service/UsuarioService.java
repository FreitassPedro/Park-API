package com.pedro.parkapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.parkapi.entity.Usuario;
import com.pedro.parkapi.repository.UsuarioRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
        
    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
       Optional<Usuario> obj = usuarioRepository.findById(id);
       return obj.get();
    }


    @Transactional
    public Usuario editarSenha(Long id, String password) {
        Usuario user = buscarPorId(id);
        user.setPassword(password);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}