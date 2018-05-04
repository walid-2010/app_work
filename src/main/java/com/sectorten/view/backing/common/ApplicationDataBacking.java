/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.common;

import com.sectorten.model.dao.masterdata.ComponentsDao;
import com.sectorten.model.dao.masterdata.CurrencyDao;
import com.sectorten.model.dao.masterdata.CustomerTypeDao;
import com.sectorten.model.dao.masterdata.ModulesDao;
import com.sectorten.model.dao.masterdata.NationalityDao;
import com.sectorten.model.entities.masterdata.Components;
import com.sectorten.model.entities.masterdata.Modules;
import com.sectorten.model.entities.transactions.Currency;
import com.sectorten.model.entities.transactions.Customertype;
import com.sectorten.model.entities.transactions.Nationality;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author al-kamel
 */
@Named(value = "applicationDataBacking")
@ApplicationScoped
public class ApplicationDataBacking implements Serializable {

    private List<Components> componentList;
    private List<Modules> moduleList;

    private List<Currency> currencyList;
    private List<Nationality> nationalitysList;
    private List<Customertype> customertypesList;

    @Inject
    private ComponentsDao componentsDao;

    @Inject
    private ModulesDao modulesDao;

    @Inject
    private NationalityDao nationalityDao;

    @Inject
    private CustomerTypeDao customerTypeDao;

    @Inject
    private CurrencyDao currencyDao;

    @PostConstruct
    public void init() {
        try {
            componentList = componentsDao.findMoulesComponents();
            moduleList = modulesDao.findAll();
            nationalitysList = nationalityDao.findAll();
            customertypesList = customerTypeDao.findAll();
            currencyList = currencyDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Modules> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Modules> moduleList) {
        this.moduleList = moduleList;
    }

    public List<Components> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Components> componentList) {
        this.componentList = componentList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Nationality> getNationalitysList() {
        return nationalitysList;
    }

    public void setNationalitysList(List<Nationality> nationalitysList) {
        this.nationalitysList = nationalitysList;
    }

    public List<Customertype> getCustomertypesList() {
        return customertypesList;
    }

    public void setCustomertypesList(List<Customertype> customertypesList) {
        this.customertypesList = customertypesList;
    }

}
