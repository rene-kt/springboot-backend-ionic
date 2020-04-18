/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.renejr.spring.enums.EstadoPagamento;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Informática
 */
@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer num_parcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer num_parcelas, Integer id, EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.num_parcelas = num_parcelas;
    }

    public Integer getNum_parcelas() {
        return num_parcelas;
    }

    public void setNum_parcelas(Integer num_parcelas) {
        this.num_parcelas = num_parcelas;
    }

    
   
    
}
