/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renejr.spring.domain.PagamentoComBoleto;
import com.renejr.spring.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 *
 * @author Informática
 * 
 * Classr responsável por serializar O pagamento com boleto e com cartão, 
 * mostar no json bonitinho
 */
@Configuration
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {

            public void configure(ObjectMapper objectMapper) {

                objectMapper.registerSubtypes(PagamentoComCartao.class);

                objectMapper.registerSubtypes(PagamentoComBoleto.class);

                super.configure(objectMapper);
            }

        };
        return builder;
    }
}
