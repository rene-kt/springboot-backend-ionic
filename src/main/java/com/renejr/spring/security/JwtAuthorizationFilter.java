/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * Essa classe é reponsável verificar se o token é válido, ou seja, se o usuário logado,
 * baseado no token feito pela classe JwtUtil
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    
    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;
    
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetatailsService) {
        super(authenticationManager);
        
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetatailsService;
    }
    
    //Método responsável por pegar apenas o token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        String header = request.getHeader("Authorization");
        
        if (header != null && header.startsWith("Bearer ")) {
            
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
            
            if (auth != null) {
                //Se o auth der certo, então a autenticação deu certo
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        chain.doFilter(request, response);
    }
    
    //Verificando se o token é valido com a classe JwtUtil
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if(jwtUtil.tokenValido(token)){
            String username = jwtUtil.getUsername(token);
            
            UserDetails user = userDetailsService.loadUserByUsername(username);
            
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        
        return null;
    }
    
}
