/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "nationality")

public class Nationality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nationality_ID")
    private Integer nationalityID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nationality")
    private String nationality;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<PurchaseInvoice> purchaseInvoiceList;
    @OneToMany(mappedBy = "nationality", fetch = FetchType.LAZY)
    private List<Names> namesList;

    public Nationality() {
    }

    public Nationality(Integer nationalityID) {
        this.nationalityID = nationalityID;
    }

    public Nationality(Integer nationalityID, String nationality) {
        this.nationalityID = nationalityID;
        this.nationality = nationality;
    }

    public Integer getNationalityID() {
        return nationalityID;
    }

    public void setNationalityID(Integer nationalityID) {
        this.nationalityID = nationalityID;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<PurchaseInvoice> getPurchaseInvoiceList() {
        return purchaseInvoiceList;
    }

    public void setPurchaseInvoiceList(List<PurchaseInvoice> purchaseInvoiceList) {
        this.purchaseInvoiceList = purchaseInvoiceList;
    }

    public List<Names> getNamesList() {
        return namesList;
    }

    public void setNamesList(List<Names> namesList) {
        this.namesList = namesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nationalityID != null ? nationalityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nationality)) {
            return false;
        }
        Nationality other = (Nationality) object;
        if ((this.nationalityID == null && other.nationalityID != null) || (this.nationalityID != null && !this.nationalityID.equals(other.nationalityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Nationality[ nationalityID=" + nationalityID + " ]";
    }

}
