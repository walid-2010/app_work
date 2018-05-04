/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.masterdata;

import com.sectorten.business.services.masterData.ImasterDataService;
import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.masterdata.ModulesOperationsDao;
import com.sectorten.model.dao.masterdata.UserDao;
import com.sectorten.model.entities.masterdata.Components;
import com.sectorten.model.entities.masterdata.Modules;
import com.sectorten.model.entities.masterdata.ModulesOperations;
import com.sectorten.model.entities.masterdata.User;
import com.sectorten.model.entities.masterdata.UserModules;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.ApplicationDataBacking;
import com.sectorten.view.common.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author al-kamel
 */
@Named
@ViewScoped
public class UserModulesBacking implements Serializable {

    @Inject
    private ImasterDataService masterDataService;

    @Inject
    private ModulesOperationsDao modulesOperationsDao;

    @Inject
    private UserDao userDao;

    @Inject
    private ApplicationDataBacking applicationDataBacking;

    private List<Components> compoentList;

    private List<ModulesOperations> modulesOperationses;

    private List<User> userList;

    private User currentSelectedUser;

    @PostConstruct
    public void init() {
        try {

            modulesOperationses = modulesOperationsDao.findAll();

            compoentList = applicationDataBacking.getComponentList();

            userList = userDao.findAll();

        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }
    }

    public boolean checkModuleSelectedBefor() {
        boolean valide = true;

        Set<Modules> modules = new HashSet<>();

        List<UserModules> userModules = currentSelectedUser.getUserModules();

        List<Modules> duplicatedList = new ArrayList<>();

        if (userModules != null && !userModules.isEmpty()) {
            for (UserModules userModElem : userModules) {
                if (!modules.contains(userModElem.getModules())) {
                    modules.add(userModElem.getModules());
                } else {
                    duplicatedList.add(userModElem.getModules());
                }
            }
        }

        if (!duplicatedList.isEmpty()) {
            for (Modules dupElem : duplicatedList) {
                String itemCodeMessage = JsfUtil.getMessageFromBunndle("modules") + "-" + dupElem.getDescription();
                String duplicatesMessage = JsfUtil.getMessageFromBunndle("selected_more_times");
                JsfUtil.addErrorMessage(itemCodeMessage + ":" + duplicatesMessage);

            }

            valide = false;
        }

        return valide;
    }

    public void removeUserModule(UserModules userModules) {
        currentSelectedUser.getUserModules().remove(userModules);

    }

    public void addNewUserModules() {
        UserModules userModules = new UserModules();
        userModules.setUser(currentSelectedUser);
        userModules.setOperationId(new ModulesOperations(1));
        if (currentSelectedUser.getUserModules() == null) {
            currentSelectedUser.setUserModules(new ArrayList<UserModules>());
        }

        currentSelectedUser.getUserModules().add(userModules);

    }

    public void saveUser() {
        try {

            if (checkModuleSelectedBefor()) {

                masterDataService.addEditUser(currentSelectedUser);
                JsfUtil.addMessageFromBundle("save_operation_success", ConstantStrings.SEVERITY_INFO);

            }

        } catch (Exception exception) {

            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }

    }

    public List<Components> getCompoentList() {
        return compoentList;
    }

    public void setCompoentList(List<Components> compoentList) {
        this.compoentList = compoentList;
    }

    public List<ModulesOperations> getModulesOperationses() {
        return modulesOperationses;
    }

    public void setModulesOperationses(List<ModulesOperations> modulesOperationses) {
        this.modulesOperationses = modulesOperationses;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getCurrentSelectedUser() {
        return currentSelectedUser;
    }

    public void setCurrentSelectedUser(User currentSelectedUser) {
        this.currentSelectedUser = currentSelectedUser;
    }

}
