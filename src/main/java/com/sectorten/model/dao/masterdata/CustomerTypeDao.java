/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao.masterdata;

import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.entities.transactions.Customertype;
import javax.ejb.Stateless;

/**
 *
 * @author al-kamel
 */
@Stateless
public class CustomerTypeDao extends AbstractDao<Customertype> {

    public CustomerTypeDao() {
        super(Customertype.class);
    }

}
