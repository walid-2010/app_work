/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.enums;

/**
 *
 * @author nbb
 */
public enum ModulesEnum {

    USERS(1),
    COMANIES(2);

    private Integer code;

    private ModulesEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
