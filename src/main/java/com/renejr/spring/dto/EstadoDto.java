/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.dto;

import com.renejr.spring.domain.Estado;
import java.io.Serializable;

/**
 *
 * @author Informática
 */
public class EstadoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    
    public EstadoDto(){
        
    }

    public EstadoDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

      
    public EstadoDto(Estado estado){
        id = estado.getId();
        nome = estado.getNome();
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "EstadoDto{" + "id=" + id + ", nome=" + nome + '}';
    }
    
    
}
