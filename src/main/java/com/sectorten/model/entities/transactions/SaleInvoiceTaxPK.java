/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author al-kamel
 */
@Embeddable
public class SaleInvoiceTaxPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "InvoiceId")
    private int invoiceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TaxId")
    private int taxId;

    public SaleInvoiceTaxPK() {
    }

    public SaleInvoiceTaxPK(int invoiceId, int taxId) {
        this.invoiceId = invoiceId;
        this.taxId = taxId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) invoiceId;
        hash += (int) taxId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleInvoiceTaxPK)) {
            return false;
        }
        SaleInvoiceTaxPK other = (SaleInvoiceTaxPK) object;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if (this.taxId != other.taxId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.SaleInvoiceTaxPK[ invoiceId=" + invoiceId + ", taxId=" + taxId + " ]";
    }

}
