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
@Table(name = "sale_invoice_tax")

public class SaleInvoiceTax implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SaleInvoiceTaxPK saleInvoiceTaxPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Value")
    private Double value;

    public SaleInvoiceTax() {
    }

    public SaleInvoiceTax(SaleInvoiceTaxPK saleInvoiceTaxPK) {
        this.saleInvoiceTaxPK = saleInvoiceTaxPK;
    }

    public SaleInvoiceTax(int invoiceId, int taxId) {
        this.saleInvoiceTaxPK = new SaleInvoiceTaxPK(invoiceId, taxId);
    }

    public SaleInvoiceTaxPK getSaleInvoiceTaxPK() {
        return saleInvoiceTaxPK;
    }

    public void setSaleInvoiceTaxPK(SaleInvoiceTaxPK saleInvoiceTaxPK) {
        this.saleInvoiceTaxPK = saleInvoiceTaxPK;
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
        hash += (saleInvoiceTaxPK != null ? saleInvoiceTaxPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleInvoiceTax)) {
            return false;
        }
        SaleInvoiceTax other = (SaleInvoiceTax) object;
        if ((this.saleInvoiceTaxPK == null && other.saleInvoiceTaxPK != null) || (this.saleInvoiceTaxPK != null && !this.saleInvoiceTaxPK.equals(other.saleInvoiceTaxPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.SaleInvoiceTax[ saleInvoiceTaxPK=" + saleInvoiceTaxPK + " ]";
    }

}
