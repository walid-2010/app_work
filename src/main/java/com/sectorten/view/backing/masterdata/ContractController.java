package com.sectorten.view.backing.masterdata;

import com.sectorten.model.dao.masterdata.NamesDao;
import com.sectorten.model.dao.masterdata.PackgeDao;
import com.sectorten.model.entities.transactions.Contract;
import com.sectorten.model.entities.transactions.Names;
import com.sectorten.model.entities.transactions.Package;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("contractController")
@ViewScoped
public class ContractController extends AutoSelectedBacking<Contract> implements Serializable {

    private List<Package> packagesList;
    private List<Names> namesList;

    @Inject
    private NamesDao namesDao;

    @Inject
    private PackgeDao packgeDao;

    public ContractController() {
        super(Contract.class);
    }

    @Override
    public void initParams() {
        super.initParams();
        namesList = namesDao.findAll();
        packagesList = packgeDao.findAll();
    }

    @Override
    public LazyEntityDataModel<Contract> getLazyItems() {
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel(getDao());
        }
        return lazyItems;
    }

    @Override
    public void save(ActionEvent event) {

        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Package> getPackagesList() {
        return packagesList;
    }

    public void setPackagesList(List<Package> packagesList) {
        this.packagesList = packagesList;
    }

    public List<Names> getNamesList() {
        return namesList;
    }

    public void setNamesList(List<Names> namesList) {
        this.namesList = namesList;
    }

}
