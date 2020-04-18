/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.repositories;

import com.renejr.spring.domain.Cliente;
import com.renejr.spring.domain.Pedido;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Informática
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
   @Transactional
    Page<Pedido> findByCliente(Cliente obj, Pageable pageRequest);
}
