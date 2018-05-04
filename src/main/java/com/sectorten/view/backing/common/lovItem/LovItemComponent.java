/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.common.lovItem;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author nbb
 */
@FacesComponent("lovItemComponent")
public class LovItemComponent extends UINamingContainer {

    private Object selected;
    private Object compValue;
    private String emptyString = "";

    public LovItemComponent() {
        super();
    }

    public void onItemChosen(ActionEvent event) {
        setSelectingObject();
    }

    public void onRowDblClckSelect(SelectEvent event) {
        setSelectingObject();
    }

    public void setSelectingObject() {
        if (selected != null) {
            compValue = selected;
            setObjectExpression(selected);
        }
    }

    public void setObjectExpression(Object selectedObject) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExpression = facesContext.getApplication().getExpressionFactory()
                .createValueExpression(elContext, "#{cc.attrs.value}", Object.class);

        valueExpression.setValue(elContext, selectedObject);
        resetResultTable();

    }

    public void clearSelection() {

        setObjectExpression(null);

    }

    public void reset(ActionEvent event) {
        resetResultTable();
        //DataTable resultsTable = (DataTable) findComponent("ItemTableId");
        //resultsTable.reset();
    }

    public void resetResultTable() {
        DataTable resultsTable = (DataTable) findComponent("ItemTableId");
        resultsTable.reset();
    }

    public Object getSelected() {
        //if(selected == null){
        selected = getAttributes().get("value");
        //}
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getCompValue() {
        //if(compValue == null)
        compValue = getAttributes().get("value");

        return compValue;
    }

    public void setCompValue(Object compValue) {
        this.compValue = compValue;
    }

    public String getEmptyString() {
        return emptyString;
    }

    public void setEmptyString(String emptyString) {
        this.emptyString = emptyString;
    }

    public String[] splitValues(String src) {
        if (src != null) {
            return src.split(",");
        }
        return null;
    }
}
