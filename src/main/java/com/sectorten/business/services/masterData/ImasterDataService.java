/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.business.services.masterData;

import com.sectorten.model.entities.masterdata.User;

/**
 *
 * @author nbb
 */
public interface ImasterDataService {

    ////////////users/////////
    public void addEditUser(User user) throws Exception;

    public void deleteUser(User user) throws Exception;

}
