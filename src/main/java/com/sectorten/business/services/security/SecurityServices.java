/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.business.services.security;

import com.sectorten.model.dao.masterdata.UserComponentsDao;
import com.sectorten.model.dao.masterdata.UserDao;
import com.sectorten.model.dao.masterdata.UserModulesDao;
import com.sectorten.model.entities.masterdata.Components;
import com.sectorten.model.entities.masterdata.Modules;
import com.sectorten.model.entities.masterdata.User;
import com.sectorten.model.entities.masterdata.UserComponents;
import com.sectorten.model.entities.masterdata.UserModules;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author nbb
 */
@Stateless
public class SecurityServices implements ISecurityServices {

    @Inject
    private UserDao UserDao;
    @Inject
    private UserComponentsDao userComponentsDao;

    @Inject
    private UserModulesDao userModulesDao;

    @Override
    public User authunticateUser(String uName, String password) {
        try {
            return UserDao.authunticateUser(uName, password);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public void saveUserComponents(List<Components> componentList, List<User> userList) throws Exception {
        try {
            if (componentList != null && !componentList.isEmpty()) {
                if (userList != null && !userList.isEmpty()) {
                    UserComponents userComponents = null;
                    for (Components components : componentList) {
                        if (components.getComponentsList() != null && !componentList.isEmpty()) {
                            saveUserComponents(components.getComponentsList(), userList);
                        } else {
                            for (User user : userList) {
                                userComponents = new UserComponents();
                                userComponents.setUser(user);
                                userComponents.setComponents(components);
                                userComponentsDao.create(userComponents);
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void saveUserMoudels(List<Modules> modulesList, List<User> userList) throws Exception {
        try {
            if (modulesList != null && !modulesList.isEmpty()) {
                if (userList != null && !userList.isEmpty()) {
                    UserModules userModules = null;
                    for (Modules modules : modulesList) {

                        for (User user : userList) {
                            userModules = new UserModules();
                            userModules.setUser(user);
                            userModules.setModules(modules);
                            userModulesDao.create(userModules);
                        }

                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
