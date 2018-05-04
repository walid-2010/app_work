package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.Company;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("companyController")
@ViewScoped
public class CompanyController extends AutoSelectedBacking<Company> implements Serializable {

    public CompanyController() {
        super(Company.class);
    }

    @Override
    public LazyEntityDataModel<Company> getLazyItems() {
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel(getDao());
        }
        return lazyItems;
    }

    @Override
    public void save(ActionEvent event) {

        super.save(event); //To change body of generated methods, choose Tools | Templates.
    }
}
