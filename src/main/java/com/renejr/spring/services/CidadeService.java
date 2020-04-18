/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Cidade;
import com.renejr.spring.repositories.CidadeRepository;
import com.renejr.spring.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Informática
 */
@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository repo;
    
    
    public Cidade find(Integer id){
        
        Optional<Cidade> est = repo.findById(id);
        
        return est.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado" + id));
        
    }
    public List<Cidade> findCidade(Integer id){
        
        return repo.findCidades(id);
    }
}
