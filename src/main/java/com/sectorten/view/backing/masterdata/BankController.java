package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.Bank;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("bankController")
@ViewScoped
public class BankController extends AutoSelectedBacking<Bank> implements Serializable {

    public BankController() {
        super(Bank.class);
    }

    @Override
    public LazyEntityDataModel<Bank> getLazyItems() {
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
