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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "jvs")

public class Jvs implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "jvs", fetch = FetchType.LAZY)
    private JvsExtend jvsExtend;
    @OneToMany(mappedBy = "nameFk", fetch = FetchType.LAZY)
    private List<JvsExtend> jvsExtendList;
    @JoinColumn(name = "Type", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jvtype type;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CreditValue")
    private Double creditValue;
    @Column(name = "OpenDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openDate;
    @Size(max = 500)
    @Column(name = "Description")
    private String description;
    @Column(name = "ExchangeID")
    private Integer exchangeID;
    @Size(max = 50)
    @Column(name = "Title")
    private String title;
    @Column(name = "Cost_Center")
    private Integer costCenter;

    @Column(name = "DepitValue")
    private Double depitValue;
    @Column(name = "OperationId")
    private Integer operationId;
    @Column(name = "Active")
    private Boolean active;
    @Size(max = 10)
    @Column(name = "BranchID")
    private String branchID;
    @Size(max = 50)
    @Column(name = "Code")
    private String code;
    @OneToMany(mappedBy = "jvsId")
    private List<Jvdetailes> jvdetailesList;

    public Jvs() {
    }

    public Jvs(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Double creditValue) {
        this.creditValue = creditValue;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExchangeID() {
        return exchangeID;
    }

    public void setExchangeID(Integer exchangeID) {
        this.exchangeID = exchangeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(Integer costCenter) {
        this.costCenter = costCenter;
    }

    public Double getDepitValue() {
        return depitValue;
    }

    public void setDepitValue(Double depitValue) {
        this.depitValue = depitValue;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Jvdetailes> getJvdetailesList() {
        return jvdetailesList;
    }

    public void setJvdetailesList(List<Jvdetailes> jvdetailesList) {
        this.jvdetailesList = jvdetailesList;
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
        if (!(object instanceof Jvs)) {
            return false;
        }
        Jvs other = (Jvs) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Jvs[ id=" + id + " ]";
    }

    public JvsExtend getJvsExtend() {
        return jvsExtend;
    }

    public void setJvsExtend(JvsExtend jvsExtend) {
        this.jvsExtend = jvsExtend;
    }

    public List<JvsExtend> getJvsExtendList() {
        return jvsExtendList;
    }

    public void setJvsExtendList(List<JvsExtend> jvsExtendList) {
        this.jvsExtendList = jvsExtendList;
    }

    public Jvtype getType() {
        return type;
    }

    public void setType(Jvtype type) {
        this.type = type;
    }

}
