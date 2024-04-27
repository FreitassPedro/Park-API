package com.pedro.parkapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j //Log para imprimir console
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final long EXPIRED_DAYS = 0;
    public static final long EXPIRED_HOURS = 0;
    public static final long EXPIRED_MINUTES = 5;

    private JwtUtils() {}

    //Responsável por gerar a chave secreta usada para assinar e verificar tokens JWT
    private static javax.crypto.SecretKey generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    //Responsável por calcular a data de expiração da token
    private static Date calculateExpirationDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRED_DAYS).plusHours(EXPIRED_HOURS).plusMinutes(EXPIRED_MINUTES);

        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String username, String role) {
        Date issuedAt = new Date();
        Date limit = calculateExpirationDate(issuedAt);

        String token = Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(limit)
                .signWith(generateKey())
                .claim("role", role)
                .compact();

        return new JwtToken(token);
    }

    //Extrai as reivindicações (claims) do token JWT fornecido
    private static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactorToken(token)).getPayload();
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return null;
    }

    public static boolean isValidToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactorToken(token));
            return true;
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return false;
    }

    //Extrai o nome do usuario (subject) retornando o nome
    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    //Garante formatação coorreta antes de ser verificado
    private static String refactorToken(String token) {
        if(token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }

}
