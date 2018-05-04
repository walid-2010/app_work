/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.business.services.security;

import com.sectorten.model.entities.masterdata.Components;
import com.sectorten.model.entities.masterdata.Modules;
import com.sectorten.model.entities.masterdata.User;
import java.util.List;

/**
 *
 * @author nbb
 */
public interface ISecurityServices {

    User authunticateUser(String uName, String password);

    public void saveUserComponents(List<Components> componentList, List<User> userList) throws Exception;

    public void saveUserMoudels(List<Modules> modulesList, List<User> userList) throws Exception;
}
