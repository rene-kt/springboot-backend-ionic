/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Informática
 * 
 * Classe mãe para os tokens JWT, ela que gera o token e faz a validação para ver se é 
 * correto através da palavra chave
 */
@Component
public class JwtUtil {

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            String username = claims.getSubject();
            Date expiration_date = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (username != null && expiration_date != null && now.before(expiration_date)) {
                return true;
            }
        }
        return false;

    }

    //Se o token for válido ,retorne o o nome do usuário
    public String getUsername(String token) {
          Claims claims = getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    //Verificar se o token passado, realmente é valido
    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();

        } catch (Exception e) {
            return null;
        }
    }

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

}
