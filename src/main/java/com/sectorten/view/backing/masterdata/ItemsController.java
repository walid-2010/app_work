package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.Items;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("itemsController")
@ViewScoped
public class ItemsController extends AutoSelectedBacking<Items> implements Serializable {

    public ItemsController() {
        super(Items.class);
    }

    @Override
    public LazyEntityDataModel<Items> getLazyItems() {
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
