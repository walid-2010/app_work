/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.business.services.masterData;

import com.sectorten.model.dao.masterdata.UserDao;
import com.sectorten.model.entities.masterdata.User;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author nbb
 */
@Stateless
public class MasterDataService implements ImasterDataService {

    @Inject
    private UserDao userDao;

    @Override
    public void addEditUser(User user) throws Exception {
        if (user.getPassWordConfirmed() != null && !user.getPassWordConfirmed().equals("")) {
            user.setPassword(user.getPassWordConfirmed());
        }

        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.edit(user);
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        userDao.remove(user);
    }

}
