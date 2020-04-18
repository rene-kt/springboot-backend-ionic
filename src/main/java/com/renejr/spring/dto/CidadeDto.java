/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.dto;

import com.renejr.spring.domain.Cidade;
import com.renejr.spring.domain.Estado;
import java.io.Serializable;

/**
 *
 * @author Informática
 */
public class CidadeDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private Estado estado;
    
    public CidadeDto(){
        
    }

    public CidadeDto(Integer id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

      
    public CidadeDto(Cidade cidade){
        id = cidade.getId();
        nome = cidade.getNome();
        estado = cidade.getEstado();
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    

    @Override
    public String toString() {
        return "CidadeDto{" + "id=" + id + ", nome=" + nome + '}';
    }
    
    
}
