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
public class ItemAccountPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ItemId")
    private int itemId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AccountId")
    private int accountId;

    public ItemAccountPK() {
    }

    public ItemAccountPK(int itemId, int accountId) {
        this.itemId = itemId;
        this.accountId = accountId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) itemId;
        hash += (int) accountId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemAccountPK)) {
            return false;
        }
        ItemAccountPK other = (ItemAccountPK) object;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.accountId != other.accountId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.ItemAccountPK[ itemId=" + itemId + ", accountId=" + accountId + " ]";
    }
    
}
