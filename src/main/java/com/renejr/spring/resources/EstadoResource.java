/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.resources;

import com.renejr.spring.domain.Cidade;
import com.renejr.spring.domain.Estado;
import com.renejr.spring.dto.CidadeDto;
import com.renejr.spring.dto.EstadoDto;
import com.renejr.spring.services.CidadeService;
import com.renejr.spring.services.EstadoService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Informática
 */
@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;
    
    @Autowired 
    private CidadeService cidadeService;
    
    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estado> findById(@PathVariable Integer id) {

        Estado obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDto>> findAllOrderByNome() {

        List<Estado> list = service.findAllOrderByNome();
        List<EstadoDto> listDto = list.stream().map(obj -> new EstadoDto(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }
    
    
    @RequestMapping(value = "/{id}/cidades" ,method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDto>> findCidades(@PathVariable Integer id) {

        List<Cidade> list = cidadeService.findCidade(id);
        List<CidadeDto> listDto = list.stream().map(obj -> new CidadeDto(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }
    
}
