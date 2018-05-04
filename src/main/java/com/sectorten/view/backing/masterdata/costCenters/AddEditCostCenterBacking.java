/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.masterdata.costCenters;

import com.sectorten.business.services.transactions.IMasterDataServices;
import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.masterdata.CostCenterDao;
import com.sectorten.model.entities.transactions.CostCenter;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.BaseBackBean;
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
 * @author waleed
 */
@Named(value = "addEditCostCenterBacking")
@ViewScoped
public class AddEditCostCenterBacking extends BaseBackBean implements Serializable {

    private CostCenter groupCostCenter;
    private CostCenter costCenter;
    private Integer addEditModeFlag;
    private Integer AddMode = ConstantStrings.ADD;
    private Integer EditMode = ConstantStrings.EDIT;
    private List<CostCenter> parentList;

    @Inject
    private CostCenterDao costCenterDao;

    @Inject
    private IMasterDataServices masterDataServices;

    /**
     * Creates a new instance of AddEditCostCenterBacking
     */
    public AddEditCostCenterBacking() {
    }

    @PostConstruct
    void init() {

        try {
            parentList = costCenterDao.findAll();

            if (ConstantStrings.ADD.equals(this.getAddEditMode()) && this.getEditedObject() != null
                    && this.getEditedObject().getClass().equals(CostCenter.class)) {
                costCenter = new CostCenter();
                groupCostCenter = (CostCenter) this.getEditedObject();
                addEditModeFlag = (Integer) this.getAddEditMode();

                this.removeFromSessionMap(AddEditCostCenterBacking.EDITED_OBJECT_KEY);
                this.removeFromSessionMap(AddEditCostCenterBacking.ADD_EDIT_MODE);

                costCenterDao.calcCostCenterParentChildCode(costCenter, groupCostCenter);

            } else if (ConstantStrings.EDIT.equals(this.getAddEditMode()) && this.getEditedObject() != null && this.getEditedObject().getClass().equals(CostCenter.class)) {
                costCenter = (CostCenter) this.getEditedObject();
                addEditModeFlag = (Integer) this.getAddEditMode();
                this.removeFromSessionMap(AddEditCostCenterBacking.EDITED_OBJECT_KEY);
                this.removeFromSessionMap(AddEditCostCenterBacking.ADD_EDIT_MODE);

                // if cost center is not one of the first dimensions
                if (costCenter.getParentId() != null) {
                    costCenter.setParentCode(costCenter.getParentId().getCode());
                } else {
                    costCenter.setParentCode("");
                }

                String[] splitCode = costCenter.getCode().split("^" + costCenter.getParentCode());
                for (String splitCode1 : splitCode) {
                    costCenter.setChildCode(splitCode1);
                }

            } else if (ConstantStrings.ADD.equals(this.getAddEditMode()) && this.getEditedObject() == null) {

                costCenter = new CostCenter();
                addEditModeFlag = (Integer) this.getAddEditMode();

                costCenterDao.calcCostCenterParentChildCode(costCenter, groupCostCenter);

                this.removeFromSessionMap(AddEditCostCenterBacking.EDITED_OBJECT_KEY);
                this.removeFromSessionMap(AddEditCostCenterBacking.ADD_EDIT_MODE);

            } else {
                this.navigate("BackToSearch");
            }
        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Save CostCenter">
    public String saveCostCenter() {
        String retURL = "";

        this.costCenter.setCode(costCenter.getParentCode() + costCenter.getChildCode());

        // if add account case
        if (Objects.equals(this.addEditModeFlag, ConstantStrings.ADD)) {

            try {

                masterDataServices.addEditCostCenter(costCenter, addEditModeFlag);

                JsfUtil.addMessageFromBundle("save_operation_success", ConstantStrings.SEVERITY_INFO);

                FacesContext.getCurrentInstance()
                        .getExternalContext().getFlash().setKeepMessages(true);

                retURL = "BackToSearch";

            } catch (Exception exception) {

                JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));

            }

        } else if (Objects.equals(this.addEditModeFlag, ConstantStrings.EDIT)) {

            try {
                masterDataServices.addEditCostCenter(costCenter, addEditModeFlag);

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
            this.putEditedObject(groupCostCenter);
        } else {
            this.putEditedObject(costCenter);
        }

        this.putAddEditMode(addEditModeFlag);

        return "Reload";
    }

    public void setGroupCostCenter(CostCenter groupCostCenter) {
        this.groupCostCenter = groupCostCenter;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public Integer getAddEditModeFlag() {
        return addEditModeFlag;
    }

    public void setAddEditModeFlag(Integer addEditModeFlag) {
        this.addEditModeFlag = addEditModeFlag;
    }

    public Integer getAddMode() {
        return AddMode;
    }

    public void setAddMode(Integer AddMode) {
        this.AddMode = AddMode;
    }

    public Integer getEditMode() {
        return EditMode;
    }

    public void setEditMode(Integer EditMode) {
        this.EditMode = EditMode;
    }

    public List<CostCenter> getParentList() {
        return parentList;
    }

    public void setParentList(List<CostCenter> parentList) {
        this.parentList = parentList;
    }

}
