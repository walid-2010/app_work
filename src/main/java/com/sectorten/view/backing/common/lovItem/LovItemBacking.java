/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.common.lovItem;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import javax.el.BeanELResolver;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author nbb
 */
@Named("lovItemBacking")
@ViewScoped
public class LovItemBacking extends BeanELResolver implements Serializable {

    private Object selected;

    public void onItemChosen(ActionEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExpression = facesContext.getApplication().getExpressionFactory()
                .createValueExpression(elContext, "#{cc.attrs.value}", Object.class);

        valueExpression.setValue(elContext, selected);
        reset(event);
    }

    public void reset(ActionEvent event) {
        DataTable resultsTable = (DataTable) findComponent("ItemTableId");
        resultsTable.reset();
    }

    public Object getSelected() {
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public UIComponent findComponent(final String id) {

        return FacesContext.getCurrentInstance().getViewRoot().findComponent(id);

    }

    public String[] splitValues(String src) {
        if (src != null) {
            return src.split(",");
        }
        return null;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property)
            throws NullPointerException, PropertyNotFoundException, ELException {
        if (property == null || base == null || base instanceof ResourceBundle || base instanceof Map || base instanceof Collection) {
            return null;
        }

        String propertyString = property.toString();

        if (propertyString.contains(".")) {
            Object value = base;

            for (String propertyPart : propertyString.split("\\.")) {
                value = super.getValue(context, value, propertyPart);
            }

            return value;
        } else {
            return super.getValue(context, base, property);
        }
    }

    public Object findBeanProperty(Object base, Object property) {
        ELContext context = FacesContext.getCurrentInstance().getELContext();
        return getValue(context, base, property);
    }

}
