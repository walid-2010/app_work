package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.Checks;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("checksController")
@ViewScoped
public class ChecksController extends AutoSelectedBacking<Checks> implements Serializable {

    public ChecksController() {
        super(Checks.class);
    }

    @Override
    public LazyEntityDataModel<Checks> getLazyItems() {
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
