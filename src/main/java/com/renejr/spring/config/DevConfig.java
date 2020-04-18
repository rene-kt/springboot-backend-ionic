/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.config;

import com.renejr.spring.services.DBService;
import com.renejr.spring.services.EmailService;
import com.renejr.spring.services.MockEmailService;
import com.renejr.spring.services.SmtpEmailService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Informática
 */
@Configuration
@Profile("dev")
public class DevConfig {
    
    @Autowired
    private DBService dbService;

   @Bean
   public boolean instatiateDatabase() throws ParseException{
       
       dbService.instantiateTestDatabase();
       return true;
   }

   @Bean
   public EmailService emailService(){
       return new SmtpEmailService();
   }
}
