/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.masterdata;

import com.sectorten.business.services.masterData.ImasterDataService;
import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.entities.masterdata.User;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.JsfUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author nbb
 */
@ViewScoped
@Named
public class UsersBacking extends AutoSelectedBacking<User> implements Serializable {

    private User searchingUser;

    @Inject
    private ImasterDataService masterDataService;

    private String confirmedPassWord;

    @PostConstruct
    @Override
    public void initParams() {
        try {
            searchingUser = new User();

            if (getUser().getIsAdmin()) {
                addNewUser();

                //compoentList = applicationDataBacking.getComponentList();
            } else {
                searchingUser.setUsername(getUser().getUsername());
                searchingUser.setId(getUser().getId());

                advanceSearch();
            }

        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }
    }

    public void addNewUser() {
        User user = new User();
        this.setSelecedObjectAfterChange(user);
        setCreateMode(true);

    }

    public void prePareAdvancedSearch() {
        searchingUser = new User();

        this.getLazyItems().setSearchingObject(searchingUser);
        setAdvancedSearch(true);
        setCreateMode(false);
    }

    @Override
    public void prePareSelectedObject(User selectedObject) {
        setCreateMode(false);
        setSelectedAfterChange(selectedObject);
    }

    public User getSearchingUser() {
        return searchingUser;
    }

    public void setSearchingUser(User searchingUser) {
        this.searchingUser = searchingUser;
    }

    @Override
    public void advanceSearch() {
        this.getLazyItems().setSearchingObject(searchingUser);
        setCreateMode(false);

    }

    public void deleteUser() {
        try {
            masterDataService.deleteUser(this.getSelected());
            JsfUtil.addMessageFromBundle("FinanceDAO_MESSAGE_DELETE_CHECK_SUCCESS", ConstantStrings.SEVERITY_INFO);
        } catch (Exception exception) {

            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }

    }

    public void deleteSelectedUser(User user) {
        try {
            masterDataService.deleteUser(user);
            JsfUtil.addMessageFromBundle("FinanceDAO_MESSAGE_DELETE_CHECK_SUCCESS", ConstantStrings.SEVERITY_INFO);
        } catch (Exception exception) {

            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }

    }

    public void saveUser() {
        try {

            masterDataService.addEditUser(this.getSelected());
            JsfUtil.addMessageFromBundle("save_operation_success", ConstantStrings.SEVERITY_INFO);

            //RequestContext.getCurrentInstance().getAjaxRequestBuilder().update(form, null);
            //RequestContext.getCurrentInstance().getAjaxRequestBuilder().update(detailsForm, null);
            prePareAdvancedSearch();

        } catch (Exception exception) {

            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }

    }

    public void confirmPassword(FacesContext context, UIComponent component, Object value) {
        try {
            UIInput Input = (UIInput) component;

            if (confirmedPassWord != null && !value.equals(confirmedPassWord)) {
                // getSelected().setPassWordConfirmed(null);
                Input.setValid(false);
                JsfUtil.addMessageFromBundle("password_mismatch", ConstantStrings.SEVERITY_ERROR);

            }

        } catch (Exception ex) {
            Logger.getLogger(UsersBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void confirmPasswordCHeck(FacesContext context, UIComponent component, Object value) {
        try {
            UIInput Input = (UIInput) component;

            if (getSelected().getPassWordConfirmed() != null && !value.equals(getSelected().getPassWordConfirmed())) {
                //setConfirmedPassWord(null);
                Input.setValid(false);
                JsfUtil.addMessageFromBundle("password_mismatch", ConstantStrings.SEVERITY_ERROR);

            }

        } catch (Exception ex) {
            Logger.getLogger(UsersBacking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getConfirmedPassWord() {
        return confirmedPassWord;
    }

    public void setConfirmedPassWord(String confirmedPassWord) {
        this.confirmedPassWord = confirmedPassWord;
    }

}
