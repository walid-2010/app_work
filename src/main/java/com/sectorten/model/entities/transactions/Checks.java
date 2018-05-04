/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import com.sectorten.model.entities.masterdata.User;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "checks")

public class Checks implements Serializable {

    @JoinColumn(name = "Type", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Checktype type;
    @JoinColumn(name = "Bank", referencedColumnName = "bank_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bank bank;
    @JoinColumn(name = "CostCenter", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CostCenter costCenter;
    @JoinColumn(name = "Issuedby", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User issuedby;
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "checks_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "checks_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "Checkno")
    private String checkno;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "ExchangeID")
    private BigInteger exchangeID;

    @Column(name = "PAymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pAymentDate;
    @Size(max = 350)
    @Column(name = "note")
    private String note;

    @Column(name = "collected")
    private Boolean collected;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsOut")
    private boolean isOut;
    @Column(name = "MandobId")
    private Integer mandobId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "PartialCollection")
    private Boolean partialCollection;
    @Column(name = "Edited")
    private Boolean edited;
    @Column(name = "underCollection")
    private Boolean underCollection;

    @JoinColumn(name = "nameID", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names nameID;

    public Checks() {
    }

    public Checks(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckno() {
        return checkno;
    }

    public void setCheckno(String checkno) {
        this.checkno = checkno;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BigInteger getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(BigInteger exchangeID) {
        this.exchangeID = exchangeID;
    }

    public Date getPAymentDate() {
        return pAymentDate;
    }

    public void setPAymentDate(Date pAymentDate) {
        this.pAymentDate = pAymentDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getCollected() {
        return collected;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public boolean getIsOut() {
        return isOut;
    }

    public void setIsOut(boolean isOut) {
        this.isOut = isOut;
    }

    public Integer getMandobId() {
        return mandobId;
    }

    public void setMandobId(Integer mandobId) {
        this.mandobId = mandobId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getPartialCollection() {
        return partialCollection;
    }

    public void setPartialCollection(Boolean partialCollection) {
        this.partialCollection = partialCollection;
    }

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public Boolean getUnderCollection() {
        return underCollection;
    }

    public void setUnderCollection(Boolean underCollection) {
        this.underCollection = underCollection;
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
        if (!(object instanceof Checks)) {
            return false;
        }
        Checks other = (Checks) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Checks[ id=" + id + " ]";
    }

    public Checktype getType() {
        return type;
    }

    public void setType(Checktype type) {
        this.type = type;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public User getIssuedby() {
        return issuedby;
    }

    public void setIssuedby(User issuedby) {
        this.issuedby = issuedby;
    }

}
