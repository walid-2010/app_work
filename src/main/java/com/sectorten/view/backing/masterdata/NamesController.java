package com.sectorten.view.backing.masterdata;

import com.sectorten.model.dao.masterdata.ManfazDao;
import com.sectorten.model.dao.masterdata.NamesDao;
import com.sectorten.model.entities.transactions.Currency;
import com.sectorten.model.entities.transactions.Customertype;
import com.sectorten.model.entities.transactions.Manfaz;
import com.sectorten.model.entities.transactions.Names;
import com.sectorten.model.entities.transactions.Nationality;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.ApplicationDataBacking;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.JsfUtil;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("namesController")
@ViewScoped
public class NamesController extends AutoSelectedBacking<Names> implements Serializable {

    @Inject
    private ApplicationDataBacking applicationDataBacking;

    @Inject
    private NamesDao namesDao;

    @Inject
    private ManfazDao manfazDao;

    private LazyEntityDataModel<Names> namesesDataModel;
    private List<Nationality> nationalitysList;
    private List<Currency> currencysList;
    private List<Customertype> customertypesList;
    private List<Manfaz> ManfazList;

    @Override
    @PostConstruct
    public void initParams() {
        try {
            super.initParams(); //To change body of generated methods, choose Tools | Templates.
            nationalitysList = applicationDataBacking.getNationalitysList();
            currencysList = applicationDataBacking.getCurrencyList();
            customertypesList = applicationDataBacking.getCustomertypesList();
            ManfazList = manfazDao.findAll();

        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));

        }
    }

    public NamesController() {
        super(Names.class);
    }

    @Override
    public LazyEntityDataModel<Names> getLazyItems() {
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel(getDao());
        }
        return lazyItems;
    }

    @Override
    public void save(ActionEvent event) {

        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Nationality> getNationalitysList() {
        return nationalitysList;
    }

    public void setNationalitysList(List<Nationality> nationalitysList) {
        this.nationalitysList = nationalitysList;
    }

    public List<Currency> getCurrencysList() {
        return currencysList;
    }

    public void setCurrencysList(List<Currency> currencysList) {
        this.currencysList = currencysList;
    }

    public List<Customertype> getCustomertypesList() {
        return customertypesList;
    }

    public void setCustomertypesList(List<Customertype> customertypesList) {
        this.customertypesList = customertypesList;
    }

    public LazyEntityDataModel<Names> getNamesesDataModel() {
        if (namesesDataModel == null) {
            namesesDataModel = new LazyEntityDataModel<>(namesDao);
        }

        return namesesDataModel;
    }

    public void setNamesesDataModel(LazyEntityDataModel<Names> namesesDataModel) {
        this.namesesDataModel = namesesDataModel;
    }

    public List<Manfaz> getManfazList() {
        return ManfazList;
    }

    public void setManfazList(List<Manfaz> ManfazList) {
        this.ManfazList = ManfazList;
    }

}
