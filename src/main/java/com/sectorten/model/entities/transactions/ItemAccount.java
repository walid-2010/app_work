/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "item_account")

public class ItemAccount implements Serializable {

    @JoinColumn(name = "AccountId", referencedColumnName = "Account_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ChartOfAccount chartOfAccount;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemAccountPK itemAccountPK;

    public ItemAccount() {
    }

    public ItemAccount(ItemAccountPK itemAccountPK) {
        this.itemAccountPK = itemAccountPK;
    }

    public ItemAccount(int itemId, int accountId) {
        this.itemAccountPK = new ItemAccountPK(itemId, accountId);
    }

    public ItemAccountPK getItemAccountPK() {
        return itemAccountPK;
    }

    public void setItemAccountPK(ItemAccountPK itemAccountPK) {
        this.itemAccountPK = itemAccountPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemAccountPK != null ? itemAccountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemAccount)) {
            return false;
        }
        ItemAccount other = (ItemAccount) object;
        if ((this.itemAccountPK == null && other.itemAccountPK != null) || (this.itemAccountPK != null && !this.itemAccountPK.equals(other.itemAccountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.ItemAccount[ itemAccountPK=" + itemAccountPK + " ]";
    }

    public ChartOfAccount getChartOfAccount() {
        return chartOfAccount;
    }

    public void setChartOfAccount(ChartOfAccount chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }

}
