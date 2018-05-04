/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "barcode")

public class Barcode implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BarcodePK barcodePK;

    public Barcode() {
    }

    public Barcode(BarcodePK barcodePK) {
        this.barcodePK = barcodePK;
    }

    public Barcode(int itemID, int quantity) {
        this.barcodePK = new BarcodePK(itemID, quantity);
    }

    public BarcodePK getBarcodePK() {
        return barcodePK;
    }

    public void setBarcodePK(BarcodePK barcodePK) {
        this.barcodePK = barcodePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (barcodePK != null ? barcodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barcode)) {
            return false;
        }
        Barcode other = (Barcode) object;
        if ((this.barcodePK == null && other.barcodePK != null) || (this.barcodePK != null && !this.barcodePK.equals(other.barcodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Barcode[ barcodePK=" + barcodePK + " ]";
    }

}
