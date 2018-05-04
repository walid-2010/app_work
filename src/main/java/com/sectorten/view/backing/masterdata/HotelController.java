package com.sectorten.view.backing.masterdata;

import com.sectorten.model.entities.transactions.Hotel;
import com.sectorten.view.backing.common.AutoSelectedBacking;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("hotelController")
@ViewScoped
public class HotelController extends AutoSelectedBacking<Hotel> implements Serializable {

    public HotelController() {
        super(Hotel.class);
    }

    @Override
    public LazyEntityDataModel<Hotel> getLazyItems() {
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
