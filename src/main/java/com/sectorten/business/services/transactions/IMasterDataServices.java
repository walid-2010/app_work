/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.business.services.transactions;

import com.sectorten.model.entities.transactions.ChartOfAccount;
import com.sectorten.model.entities.transactions.CostCenter;

/**
 *
 * @author al-kamel
 */
public interface IMasterDataServices {

    public Object saveAddEditCostCenter(CostCenter costCenter, Integer addEditFlag) throws Exception;

    public Object addEditCostCenter(CostCenter costCenter, Integer addEditFlag) throws Exception;

    public void removeCostCenter(CostCenter costCenter) throws Exception;

    public Object saveAddEditChartOfAccount(ChartOfAccount chartOfAccount, Integer addEditFlag) throws Exception;

    public Object addEditChartOfAccount(ChartOfAccount chartOfAccount, Integer addEditFlag) throws Exception;

    public void removeChartOfAccount(ChartOfAccount chartOfAccount) throws Exception;
}
