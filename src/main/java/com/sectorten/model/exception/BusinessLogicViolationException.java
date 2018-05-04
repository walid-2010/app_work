/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.exception;

/**
 *
 * @author
 */
public class BusinessLogicViolationException extends RuntimeException {

    public BusinessLogicViolationException(String message) {
        super(message);
    }
}
