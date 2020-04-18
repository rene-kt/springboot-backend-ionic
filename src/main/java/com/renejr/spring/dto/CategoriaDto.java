/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.dto;

import com.renejr.spring.domain.Categoria;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Informática
 */
public class CategoriaDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;
    
    public CategoriaDto(){
        
    }
    
    public CategoriaDto(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();
    }
    public CategoriaDto(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
}
