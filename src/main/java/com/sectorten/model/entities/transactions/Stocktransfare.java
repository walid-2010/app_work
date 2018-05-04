/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "stocktransfare")

public class Stocktransfare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "Code")
    private String code;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 250)
    @Column(name = "Description")
    private String description;
    @Column(name = "FromStore")
    private Integer fromStore;
    @Column(name = "toStore")
    private Integer toStore;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TransNetTotal")
    private Double transNetTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISReturn")
    private boolean iSReturn;
    @Column(name = "Byuser")
    private Integer byuser;
    @Column(name = "ByModifier")
    private Integer byModifier;
    @OneToMany(mappedBy = "transId")
    private List<StockTransDetails> stockTransDetailsList;

    public Stocktransfare() {
    }

    public Stocktransfare(Integer id) {
        this.id = id;
    }

    public Stocktransfare(Integer id, boolean iSReturn) {
        this.id = id;
        this.iSReturn = iSReturn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFromStore() {
        return fromStore;
    }

    public void setFromStore(Integer fromStore) {
        this.fromStore = fromStore;
    }

    public Integer getToStore() {
        return toStore;
    }

    public void setToStore(Integer toStore) {
        this.toStore = toStore;
    }

    public Double getTransNetTotal() {
        return transNetTotal;
    }

    public void setTransNetTotal(Double transNetTotal) {
        this.transNetTotal = transNetTotal;
    }

    public boolean getISReturn() {
        return iSReturn;
    }

    public void setISReturn(boolean iSReturn) {
        this.iSReturn = iSReturn;
    }

    public Integer getByuser() {
        return byuser;
    }

    public void setByuser(Integer byuser) {
        this.byuser = byuser;
    }

    public Integer getByModifier() {
        return byModifier;
    }

    public void setByModifier(Integer byModifier) {
        this.byModifier = byModifier;
    }

    public List<StockTransDetails> getStockTransDetailsList() {
        return stockTransDetailsList;
    }

    public void setStockTransDetailsList(List<StockTransDetails> stockTransDetailsList) {
        this.stockTransDetailsList = stockTransDetailsList;
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
        if (!(object instanceof Stocktransfare)) {
            return false;
        }
        Stocktransfare other = (Stocktransfare) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Stocktransfare[ id=" + id + " ]";
    }

}
