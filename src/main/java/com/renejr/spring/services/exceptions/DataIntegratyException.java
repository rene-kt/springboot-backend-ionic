/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.services.exceptions;

/**
 *
 * @author Informática
 */
public class DataIntegratyException extends RuntimeException{
    
    public DataIntegratyException(String msg){
        super(msg);
    }
    
     
    public DataIntegratyException(String msg, Throwable cause){
        super(msg, cause);
    }
}
