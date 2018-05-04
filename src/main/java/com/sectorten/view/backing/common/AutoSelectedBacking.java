package com.sectorten.view.backing.common;

import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortOrder;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AutoSelectedBacking<T> extends AbstractBacking<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public AutoSelectedBacking() {
        super();
    }

    public AutoSelectedBacking(Class<T> itemClass) {
        super(itemClass);
    }

    public void applySelection(List<T> res) {
        if (!res.isEmpty() && !isCreateMode()) {
            setSelected(res.get(0));
            prePareSelectedObject(res.get(0));
        } else {
            setSelected(null);
            prepareFormWhenEmptyResult();
        }
    }

    @Override
    public LazyEntityDataModel<T> getLazyItems() {
        AbstractBacking<T> c = this;
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel<T>(getDao()) {
                @Override
                public List<T> load(int first, int pageSize, List multiSortMeta, Map filters) {
                    List<T> res = super.load(first, pageSize, multiSortMeta, filters);
                    applySelection(res);
                    return res; //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                    List<T> res = super.load(first, pageSize, sortField, sortOrder, filters);
                    applySelection(res);
                    return res; //To change body of generated methods, choose Tools | Templates.
                }

            };
        }
        return lazyItems;
    }

    public boolean canEditFields() {
        return super.getSelected() != null && (isCreateMode() || canEdit());
    }

    public void prePareSelectedObject(T selectedObject) {

    }

    public void onRowSelect(SelectEvent event) {
        this.setSelected((T) event.getObject());

    }

    public void setSelecedObjectAfterChange(T selectedObject) {
        setSelectedAfterChange(selectedObject);
    }

    public void prepareFormWhenEmptyResult() {
    }

    public List<T> getResultList() {
        return lazyItems.getItemList();

    }

    public void advanceSearch() {

    }

}
