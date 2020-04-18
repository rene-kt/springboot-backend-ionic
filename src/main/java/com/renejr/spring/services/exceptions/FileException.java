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
public class FileException extends RuntimeException{
    
    public FileException(String msg){
        super(msg);
    }
    
     
    public FileException(String msg, Throwable cause){
        super(msg, cause);
    }
}
