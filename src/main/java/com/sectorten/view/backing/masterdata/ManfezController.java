package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.Manfaz;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("manfezController")
@ViewScoped
public class ManfezController extends AutoSelectedBacking<Manfaz> implements Serializable {

    public ManfezController() {
        super(Manfaz.class);
    }

    @Override
    public LazyEntityDataModel<Manfaz> getLazyItems() {
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
