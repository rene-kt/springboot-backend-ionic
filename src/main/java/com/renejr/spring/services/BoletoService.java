/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services;

import com.renejr.spring.domain.PagamentoComBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author Informática
 */
@Service
public class BoletoService {
    
    public static void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante){
        
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(instante);
        
        cal.add(Calendar.DAY_OF_MONTH, 7);
        
        pagto.setData_vencimento(cal.getTime());
    }
}
