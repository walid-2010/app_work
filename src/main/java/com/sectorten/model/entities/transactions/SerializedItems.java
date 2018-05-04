/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "serialized_items")

public class SerializedItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Serial")
    private String serial;
    @Column(name = "ISPurchased")
    private Boolean iSPurchased;
    @Column(name = "IsSold")
    private Boolean isSold;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Long id;
    @Column(name = "Isreturned")
    private Boolean isreturned;
    @Column(name = "PurchaseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;
    @JoinColumn(name = "itemId", referencedColumnName = "Id")
    @ManyToOne
    private Items itemId;

    public SerializedItems() {
    }

    public SerializedItems(Long id) {
        this.id = id;
    }

    public SerializedItems(Long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Boolean getISPurchased() {
        return iSPurchased;
    }

    public void setISPurchased(Boolean iSPurchased) {
        this.iSPurchased = iSPurchased;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsreturned() {
        return isreturned;
    }

    public void setIsreturned(Boolean isreturned) {
        this.isreturned = isreturned;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Items getItemId() {
        return itemId;
    }

    public void setItemId(Items itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SerializedItems)) {
            return false;
        }
        SerializedItems other = (SerializedItems) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.SerializedItems[ id=" + id + " ]";
    }

}
