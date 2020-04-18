/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.Categoria;
import com.renejr.spring.domain.Cliente;
import com.renejr.spring.dto.CategoriaDto;
import com.renejr.spring.services.exceptions.ObjectNotFoundException;
import com.renejr.spring.repositories.CategoriaRepository;
import com.renejr.spring.services.exceptions.DataIntegratyException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Informática
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj =  find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possível excluir uma categoria que possui produtos");
        }
    }
    public List<Categoria> findAll(){
        return repo.findAll();
    }
    
    public Page<Categoria> findPage(Integer page, Integer line_per_page, String orderBy, String direction){
        PageRequest page_request = PageRequest.of(page, line_per_page, Direction.valueOf(direction), orderBy);
        
        return repo.findAll(page_request);
    }
    
    public Categoria fromDto(CategoriaDto dto){
        return new Categoria(dto.getId(), dto.getNome());
    }
    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());
    }
}
