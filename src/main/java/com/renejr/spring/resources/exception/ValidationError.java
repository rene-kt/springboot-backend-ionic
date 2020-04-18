/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.renejr.spring.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Informática
 */
public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    
    private List<FieldMessage> list = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }


    public List<FieldMessage> getErrors() {
        return list;
    }
    
    public void addError(String field_name, String message){
        list.add(new FieldMessage(field_name, message));
    }
}
