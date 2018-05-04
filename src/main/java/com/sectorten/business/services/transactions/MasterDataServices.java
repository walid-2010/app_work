/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.business.services.transactions;

import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.masterdata.CostCenterDao;
import com.sectorten.model.dao.transactions.ChartOfAccountDao;
import com.sectorten.model.entities.transactions.ChartOfAccount;
import com.sectorten.model.entities.transactions.CostCenter;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author al-kamel
 */
@Stateless
public class MasterDataServices implements IMasterDataServices {

    @Inject
    private CostCenterDao costCenterDao;

    @Inject
    private ChartOfAccountDao chartOfAccountDao;

    @Override
    public Object saveAddEditCostCenter(CostCenter costCenter, Integer addEditFlag) throws Exception {

        if (ConstantStrings.ADD.equals(addEditFlag)) { // add condition

            try {

                if (costCenter.getParentId() != null) {
                    // clear parent rtl list

                    CostCenter parentCenter = costCenterDao.find(costCenter.getParentId().getId());

                    costCenterDao.edit(parentCenter);
                }

                // create account
                costCenterDao.create(costCenter);

            } catch (Exception exception) {
                throw exception;
            }
        } else if (ConstantStrings.EDIT.equals(addEditFlag)) { // edit condition
            try {
                // edit account
                costCenterDao.edit(costCenter);

            } catch (Exception exception) {
                throw exception;
            }
        }
        return costCenter;
    }

    @Override
    public Object addEditCostCenter(CostCenter costCenter, Integer addEditFlag) throws Exception {
        try {
            return this.saveAddEditCostCenter(costCenter, addEditFlag);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public void removeCostCenter(CostCenter costCenter) throws Exception {
        try {
            costCenterDao.remove(costCenter);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public Object saveAddEditChartOfAccount(ChartOfAccount chartOfAccount, Integer addEditFlag) throws Exception {
        if (ConstantStrings.ADD.equals(addEditFlag)) { // add condition

            try {

                if (chartOfAccount.getParentId() != null) {
                    // clear parent rtl list

                    CostCenter parentCenter = costCenterDao.find(chartOfAccount.getParentId().getAccountID());

                    costCenterDao.edit(parentCenter);
                }

                // create account
                chartOfAccountDao.create(chartOfAccount);

            } catch (Exception exception) {
                throw exception;
            }
        } else if (ConstantStrings.EDIT.equals(addEditFlag)) { // edit condition
            try {
                // edit account
                chartOfAccountDao.edit(chartOfAccount);

            } catch (Exception exception) {
                throw exception;
            }
        }
        return chartOfAccount;
    }

    @Override
    public Object addEditChartOfAccount(ChartOfAccount chartOfAccount, Integer addEditFlag) throws Exception {
        try {
            return this.saveAddEditChartOfAccount(chartOfAccount, addEditFlag);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public void removeChartOfAccount(ChartOfAccount chartOfAccount) throws Exception {
        try {
            chartOfAccountDao.remove(chartOfAccount);
        } catch (Exception exception) {
            throw exception;
        }
    }

}
