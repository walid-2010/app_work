/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.masterdata;

import com.sectorten.business.services.transactions.IMasterDataServices;
import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.transactions.ChartOfAccountDao;
import com.sectorten.model.entities.transactions.ChartOfAccount;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.BaseBackBean;
import com.sectorten.view.backing.masterdata.costCenters.AddEditCostCenterBacking;
import com.sectorten.view.common.JsfUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kimo
 */
@Named
@ViewScoped
public class AddEditMainChartOfAccountingBB extends BaseBackBean implements Serializable {

    private List<ChartOfAccount> parentList;
    private ChartOfAccount accountObj;
    private ChartOfAccount groupChartOfAccount;

    private Integer addEditModeFlag;

    @Inject
    private ChartOfAccountDao chartOfAccountDao;

    @Inject
    private IMasterDataServices masterDataServices;

    @PostConstruct
    void init() {

        try {
            parentList = chartOfAccountDao.findAll();

            if (ConstantStrings.ADD.equals(this.getAddEditMode()) && this.getEditedObject() != null
                    && this.getEditedObject().getClass().equals(ChartOfAccount.class)) {
                accountObj = new ChartOfAccount();
                addEditModeFlag = (Integer) this.getAddEditMode();
                groupChartOfAccount = (ChartOfAccount) this.getEditedObject();
                this.removeFromSessionMap(AddEditCostCenterBacking.EDITED_OBJECT_KEY);
                this.removeFromSessionMap(AddEditCostCenterBacking.ADD_EDIT_MODE);

            } else if (ConstantStrings.EDIT.equals(this.getAddEditMode()) && this.getEditedObject() != null && this.getEditedObject().getClass().equals(ChartOfAccount.class)) {
                accountObj = (ChartOfAccount) this.getEditedObject();
                addEditModeFlag = (Integer) this.getAddEditMode();
                this.removeFromSessionMap(AddEditCostCenterBacking.EDITED_OBJECT_KEY);
                this.removeFromSessionMap(AddEditCostCenterBacking.ADD_EDIT_MODE);

                // if cost center is not one of the first dimensions
                if (accountObj.getParentId() != null) {
                    accountObj.setParentCode(accountObj.getParentId().getAccountNo());
                } else {
                    accountObj.setParentCode("");
                }

                String[] splitCode = accountObj.getAccountNo().split("^" + accountObj.getParentCode());
                for (String splitCode1 : splitCode) {
                    accountObj.setChildCode(splitCode1);
                }

            } else if (ConstantStrings.ADD.equals(this.getAddEditMode()) && this.getEditedObject() == null) {

                accountObj = new ChartOfAccount();
                addEditModeFlag = (Integer) this.getAddEditMode();

                chartOfAccountDao.calcChartOfAccountParentChildCode(accountObj, groupChartOfAccount);

                this.removeFromSessionMap(AddEditCostCenterBacking.EDITED_OBJECT_KEY);
                this.removeFromSessionMap(AddEditCostCenterBacking.ADD_EDIT_MODE);

            }
        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Save ChartOfAccount">
    public String save() {
        String retURL = "";

        this.accountObj.setAccountNo(accountObj.getParentCode() + accountObj.getChildCode());

        // if add account case
        if (Objects.equals(this.addEditModeFlag, ConstantStrings.ADD)) {

            try {

                masterDataServices.addEditChartOfAccount(accountObj, addEditModeFlag);

                JsfUtil.addMessageFromBundle("save_operation_success", ConstantStrings.SEVERITY_INFO);

                FacesContext.getCurrentInstance()
                        .getExternalContext().getFlash().setKeepMessages(true);

                retURL = "BackToSearch";

            } catch (Exception exception) {

                JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));

            }

        } else if (Objects.equals(this.addEditModeFlag, ConstantStrings.EDIT)) {

            try {
                masterDataServices.addEditChartOfAccount(accountObj, addEditModeFlag);

                JsfUtil.addMessageFromBundle("save_operation_success", ConstantStrings.SEVERITY_INFO);

                FacesContext.getCurrentInstance()
                        .getExternalContext().getFlash().setKeepMessages(true);

                retURL = "BackToSearch";
            } catch (Exception exception) {

                JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
            }

        }

        return retURL;
    }
    //</editor-fold>

    public String resetForm() {
        if (this.addEditModeFlag.equals(ConstantStrings.ADD)) {
            this.putEditedObject(groupChartOfAccount);
        } else {
            this.putEditedObject(accountObj);
        }

        this.putAddEditMode(addEditModeFlag);

        return "Reload";
    }

    public List<ChartOfAccount> getParentList() {
        return parentList;
    }

    public void setParentList(List<ChartOfAccount> parentList) {
        this.parentList = parentList;
    }

    public ChartOfAccount getAccountObj() {
        return accountObj;
    }

    public void setAccountObj(ChartOfAccount accountObj) {
        this.accountObj = accountObj;
    }

    public ChartOfAccount getGroupChartOfAccount() {
        return groupChartOfAccount;
    }

    public void setGroupChartOfAccount(ChartOfAccount groupChartOfAccount) {
        this.groupChartOfAccount = groupChartOfAccount;
    }

    public Integer getAddEditModeFlag() {
        return addEditModeFlag;
    }

    public void setAddEditModeFlag(Integer addEditModeFlag) {
        this.addEditModeFlag = addEditModeFlag;
    }

}
