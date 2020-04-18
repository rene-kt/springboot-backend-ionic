/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.repositories;

import com.renejr.spring.domain.Estado;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Informática
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Transactional
    List<Estado> findAllByOrderByNome();

}
