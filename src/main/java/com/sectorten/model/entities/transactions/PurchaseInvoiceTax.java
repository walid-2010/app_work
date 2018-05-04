/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "purchase_invoice_tax")

public class PurchaseInvoiceTax implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PurchaseInvoiceTaxPK purchaseInvoiceTaxPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Value")
    private Double value;

    public PurchaseInvoiceTax() {
    }

    public PurchaseInvoiceTax(PurchaseInvoiceTaxPK purchaseInvoiceTaxPK) {
        this.purchaseInvoiceTaxPK = purchaseInvoiceTaxPK;
    }

    public PurchaseInvoiceTax(int invoiceId, int taxId) {
        this.purchaseInvoiceTaxPK = new PurchaseInvoiceTaxPK(invoiceId, taxId);
    }

    public PurchaseInvoiceTaxPK getPurchaseInvoiceTaxPK() {
        return purchaseInvoiceTaxPK;
    }

    public void setPurchaseInvoiceTaxPK(PurchaseInvoiceTaxPK purchaseInvoiceTaxPK) {
        this.purchaseInvoiceTaxPK = purchaseInvoiceTaxPK;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseInvoiceTaxPK != null ? purchaseInvoiceTaxPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseInvoiceTax)) {
            return false;
        }
        PurchaseInvoiceTax other = (PurchaseInvoiceTax) object;
        if ((this.purchaseInvoiceTaxPK == null && other.purchaseInvoiceTaxPK != null) || (this.purchaseInvoiceTaxPK != null && !this.purchaseInvoiceTaxPK.equals(other.purchaseInvoiceTaxPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.PurchaseInvoiceTax[ purchaseInvoiceTaxPK=" + purchaseInvoiceTaxPK + " ]";
    }

}
