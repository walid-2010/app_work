/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "jvoutdetails")

public class Jvoutdetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "JvOutDetailsID")
    private Integer jvOutDetailsID;
    @Column(name = "AccountID")
    private Integer accountID;
    @Column(name = "NameID")
    private Integer nameID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Amount")
    private Double amount;
    @Size(max = 250)
    @Column(name = "Notes")
    private String notes;
    @Column(name = "JvOutID")
    private Integer jvOutID;

    public Jvoutdetails() {
    }

    public Jvoutdetails(Integer jvOutDetailsID) {
        this.jvOutDetailsID = jvOutDetailsID;
    }

    public Integer getJvOutDetailsID() {
        return jvOutDetailsID;
    }

    public void setJvOutDetailsID(Integer jvOutDetailsID) {
        this.jvOutDetailsID = jvOutDetailsID;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getNameID() {
        return nameID;
    }

    public void setNameID(Integer nameID) {
        this.nameID = nameID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getJvOutID() {
        return jvOutID;
    }

    public void setJvOutID(Integer jvOutID) {
        this.jvOutID = jvOutID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jvOutDetailsID != null ? jvOutDetailsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jvoutdetails)) {
            return false;
        }
        Jvoutdetails other = (Jvoutdetails) object;
        if ((this.jvOutDetailsID == null && other.jvOutDetailsID != null) || (this.jvOutDetailsID != null && !this.jvOutDetailsID.equals(other.jvOutDetailsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Jvoutdetails[ jvOutDetailsID=" + jvOutDetailsID + " ]";
    }

}
