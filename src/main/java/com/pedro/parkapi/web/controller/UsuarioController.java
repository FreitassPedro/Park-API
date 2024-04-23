package com.pedro.parkapi.web.controller;

import java.util.List;

import com.pedro.parkapi.web.dto.UsuarioCreateDto;
import com.pedro.parkapi.web.dto.UsuarioResponseDTO;
import com.pedro.parkapi.web.dto.UsuarioSenhaDTO;
import com.pedro.parkapi.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.parkapi.entity.Usuario;
import com.pedro.parkapi.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(user));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDTO> getById (@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDTO(user));
    }

    /*Patch pode ser usado para modificar apenas um valor
    Ao contrario do Put que altera todos os valores
    */
    @PatchMapping("{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDTO dto) {
        Usuario user = usuarioService.editarSenha(id, dto.senhaAtual(), dto.novaSenha(), dto.confirmaSenha());

        //noContent é um código de sucesso mas que não retorna nada!
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDTO(users));

    }
}
