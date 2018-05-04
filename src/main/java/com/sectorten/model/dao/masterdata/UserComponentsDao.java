/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao.masterdata;

import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.entities.masterdata.UserComponents;
import javax.ejb.Stateless;

/**
 *
 * @author nbb
 */
@Stateless
public class UserComponentsDao extends AbstractDao<UserComponents> {

    public UserComponentsDao() {
        super(UserComponents.class);
    }

}
