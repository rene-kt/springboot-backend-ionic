/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.repositories;

import com.renejr.spring.domain.Categoria;
import com.renejr.spring.domain.Produto;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Informática
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> find(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
