/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.common;

/**
 *
 *
 */
public abstract class RegularExpression {

    public static final String Email_regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    public static final String Person_Name = "([ء-يa-zA-Z]||\\s||\\-||\\.||\\*){1,100}$";
    public static final String IP = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
}
