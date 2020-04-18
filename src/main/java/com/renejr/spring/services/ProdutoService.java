/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Categoria;
import com.renejr.spring.domain.Produto;
import com.renejr.spring.repositories.CategoriaRepository;
import com.renejr.spring.services.exceptions.ObjectNotFoundException;
import com.renejr.spring.repositories.ProdutoRepository;
import com.renejr.spring.resources.ProdutoResource;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Informática
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    
    
    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        System.out.println(nome);
        
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        
        return repo.find(nome, categorias, pageRequest);
        
    }
}
