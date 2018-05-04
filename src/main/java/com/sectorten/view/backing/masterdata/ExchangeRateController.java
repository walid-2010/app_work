package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.ExchangeRate;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("exchangeRateController")
@ViewScoped
public class ExchangeRateController extends AutoSelectedBacking<ExchangeRate> implements Serializable {

    public ExchangeRateController() {
        super(ExchangeRate.class);
    }

    @Override
    public LazyEntityDataModel<ExchangeRate> getLazyItems() {
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
