/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Estado;
import com.renejr.spring.repositories.EstadoRepository;
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
public class EstadoService {
    
    @Autowired
    private EstadoRepository repo;
    
    
    public Estado find(Integer id){
        
        Optional<Estado> est = repo.findById(id);
        
        return est.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado" + id));
        
    }
    public List<Estado> findAllOrderByNome(){
        
        return repo.findAllByOrderByNome();
    }
}
