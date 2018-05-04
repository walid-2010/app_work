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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "jvdetailes")

public class Jvdetailes implements Serializable {

    @JoinColumn(name = "AcountID", referencedColumnName = "Account_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChartOfAccount acountID;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Depit")
    private Double depit;
    @Column(name = "Credit")
    private Double credit;

    @Basic(optional = false)
    @NotNull
    @Column(name = "NameId")
    private int nameId;
    @Size(max = 100)
    @Column(name = "Notes")
    private String notes;
    @JoinColumn(name = "JvsId", referencedColumnName = "Id")
    @ManyToOne
    private Jvs jvsId;

    public Jvdetailes() {
    }

    public Jvdetailes(Integer id) {
        this.id = id;
    }

    public Jvdetailes(Integer id, int nameId) {
        this.id = id;
        this.nameId = nameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDepit() {
        return depit;
    }

    public void setDepit(Double depit) {
        this.depit = depit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Jvs getJvsId() {
        return jvsId;
    }

    public void setJvsId(Jvs jvsId) {
        this.jvsId = jvsId;
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
        if (!(object instanceof Jvdetailes)) {
            return false;
        }
        Jvdetailes other = (Jvdetailes) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Jvdetailes[ id=" + id + " ]";
    }

    public ChartOfAccount getAcountID() {
        return acountID;
    }

    public void setAcountID(ChartOfAccount acountID) {
        this.acountID = acountID;
    }

}
