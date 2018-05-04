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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "jvs_extend")

public class JvsExtend implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Column(name = "weekNo")
    private Integer weekNo;
    @Size(max = 50)
    @Column(name = "place")
    private String place;
    @JoinColumn(name = "id", referencedColumnName = "Id", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Jvs jvs;
    @JoinColumn(name = "BankID", referencedColumnName = "bank_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bankID;
    @JoinColumn(name = "name_fk", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jvs nameFk;

    public JvsExtend() {
    }

    public JvsExtend(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Integer weekNo) {
        this.weekNo = weekNo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Jvs getJvs() {
        return jvs;
    }

    public void setJvs(Jvs jvs) {
        this.jvs = jvs;
    }

    public Bank getBankID() {
        return bankID;
    }

    public void setBankID(Bank bankID) {
        this.bankID = bankID;
    }

    public Jvs getNameFk() {
        return nameFk;
    }

    public void setNameFk(Jvs nameFk) {
        this.nameFk = nameFk;
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
        if (!(object instanceof JvsExtend)) {
            return false;
        }
        JvsExtend other = (JvsExtend) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.JvsExtend[ id=" + id + " ]";
    }

}
