/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.common;

/**
 *
 * @author
 */
public enum QueryNamesEnum {

    USER_SEARCH_USER("User.searchUser");

    private String code;

    private QueryNamesEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
