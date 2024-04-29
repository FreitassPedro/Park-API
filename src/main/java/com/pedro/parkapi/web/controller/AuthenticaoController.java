package com.pedro.parkapi.web.controller;

import com.pedro.parkapi.jwt.JwtToken;
import com.pedro.parkapi.jwt.JwtUserDetailsService;
import com.pedro.parkapi.web.dto.UsuarioLoginDto;
import com.pedro.parkapi.web.dto.UsuarioResponseDTO;
import com.pedro.parkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Authentication")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthenticaoController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authManager;


    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto loginDto, HttpServletRequest request) {
        log.info("Processo de autenticação pelo login {}", loginDto.getUsername());
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authManager.authenticate(authenticationToken);

            JwtToken token = detailsService.getTokenAuthenticated(loginDto.getUsername());

            return ResponseEntity.ok(token);
        } catch (AuthenticationException error) {
            log.error("Bad Credentials from username '{}'", loginDto.getUsername());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas"));

    }
}