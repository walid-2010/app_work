/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao;

import com.sectorten.model.entities.transactions.Store;
import javax.ejb.Stateless;

/**
 *
 * @author al-kamel
 */
@Stateless
public class StoreDao extends AbstractDao<Store> {

    public StoreDao() {
        super(Store.class);
    }

}
