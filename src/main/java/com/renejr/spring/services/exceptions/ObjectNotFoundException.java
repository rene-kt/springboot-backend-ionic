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
public class ObjectNotFoundException extends RuntimeException{
    
    public ObjectNotFoundException(String msg){
        super(msg);
    }
    
     
    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
