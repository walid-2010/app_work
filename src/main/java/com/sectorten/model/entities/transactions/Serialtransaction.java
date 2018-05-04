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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "serialtransaction")

public class Serialtransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "itemid")
    private Integer itemid;
    @Column(name = "fromserial")
    private Integer fromserial;
    @Column(name = "toserial")
    private Integer toserial;
    @Column(name = "inoutVar")
    private Short inoutVar;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "serialactionordersid")
    private Integer serialactionordersid;
    @Column(name = "billid")
    private Integer billid;
    @Column(name = "billdetailid")
    private Integer billdetailid;
    @Column(name = "storeid")
    private Integer storeid;

    public Serialtransaction() {
    }

    public Serialtransaction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getFromserial() {
        return fromserial;
    }

    public void setFromserial(Integer fromserial) {
        this.fromserial = fromserial;
    }

    public Integer getToserial() {
        return toserial;
    }

    public void setToserial(Integer toserial) {
        this.toserial = toserial;
    }

    public Short getInoutVar() {
        return inoutVar;
    }

    public void setInoutVar(Short inoutVar) {
        this.inoutVar = inoutVar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSerialactionordersid() {
        return serialactionordersid;
    }

    public void setSerialactionordersid(Integer serialactionordersid) {
        this.serialactionordersid = serialactionordersid;
    }

    public Integer getBillid() {
        return billid;
    }

    public void setBillid(Integer billid) {
        this.billid = billid;
    }

    public Integer getBilldetailid() {
        return billdetailid;
    }

    public void setBilldetailid(Integer billdetailid) {
        this.billdetailid = billdetailid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
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
        if (!(object instanceof Serialtransaction)) {
            return false;
        }
        Serialtransaction other = (Serialtransaction) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Serialtransaction[ id=" + id + " ]";
    }

}
