/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "contract")

public class Contract implements Serializable {

    @JoinColumn(name = "Name_ID", referencedColumnName = "Name_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Names nameID;
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "contarct_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contarctDate;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "pakage", referencedColumnName = "Pakage_ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Package pakage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "first_payment")
    private BigDecimal firstPayment;
    @Id
    @TableGenerator(name = "contract_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "contract_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ContractID")
    private Integer contractID;
    @Size(max = 50)
    @Column(name = "code")
    private String code;
    @Column(name = "NoOfMonths")
    private Integer noOfMonths;

    public Contract() {
    }

    public Contract(Integer contractID) {
        this.contractID = contractID;
    }

    public Date getContarctDate() {
        return contarctDate;
    }

    public void setContarctDate(Date contarctDate) {
        this.contarctDate = contarctDate;
    }

    public Package getPakage() {
        return pakage;
    }

    public void setPakage(Package pakage) {
        this.pakage = pakage;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(BigDecimal firstPayment) {
        this.firstPayment = firstPayment;
    }

    public Integer getContractID() {
        return contractID;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNoOfMonths() {
        return noOfMonths;
    }

    public void setNoOfMonths(Integer noOfMonths) {
        this.noOfMonths = noOfMonths;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractID != null ? contractID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.contractID == null && other.contractID != null) || (this.contractID != null && !this.contractID.equals(other.contractID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Contract[ contractID=" + contractID + " ]";
    }

    public Names getNameID() {
        return nameID;
    }

    public void setNameID(Names nameID) {
        this.nameID = nameID;
    }

}
