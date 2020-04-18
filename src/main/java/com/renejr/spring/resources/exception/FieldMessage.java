/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.resources.exception;

import java.io.Serializable;

/**
 *
 * @author Informática
 */
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L; 
    
    private String field_name;
    private String message;
    
    public FieldMessage(){
        
    }

    public FieldMessage(String field_name, String message) {
        this.field_name = field_name;
        this.message = message;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
