/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "sale_invoice")

public class SaleInvoice implements Serializable {

    @JoinColumn(name = "CostCenter", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CostCenter costCenter;
    @JoinColumn(name = "Customer", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names customer;

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
    @Column(name = "MandobName")
    private Integer mandobName;
    @Column(name = "SalesManName")
    private Integer salesManName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "InvoiceTotal")
    private Double invoiceTotal;
    @Column(name = "PercDiscount")
    private Double percDiscount;
    @Column(name = "CashDiscount")
    private Double cashDiscount;
    @Column(name = "InvoiceNetTotal")
    private Double invoiceNetTotal;
    @Column(name = "InvoiceNetTotalTax")
    private Double invoiceNetTotalTax;
    @Column(name = "IsRet")
    private Boolean isRet;
    @Column(name = "ByUSer")
    private Integer byUSer;
    @Column(name = "Modifier")
    private Integer modifier;
    @Column(name = "ExchangeRate")
    private BigInteger exchangeRate;

    @Column(name = "TaxDeduct")
    private Double taxDeduct;
    @Column(name = "InvoiceNetTotalTaxDeduct")
    private Double invoiceNetTotalTaxDeduct;
    @Column(name = "IsSuspended")
    private Boolean isSuspended;
    @PrivateOwned
    @OneToMany(mappedBy = "invoiceId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SaleInvoiceDetails> saleInvoiceDetailsList;

    public SaleInvoice() {
    }

    public SaleInvoice(Integer id) {
        this.id = id;
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

    public Integer getMandobName() {
        return mandobName;
    }

    public void setMandobName(Integer mandobName) {
        this.mandobName = mandobName;
    }

    public Integer getSalesManName() {
        return salesManName;
    }

    public void setSalesManName(Integer salesManName) {
        this.salesManName = salesManName;
    }

    public Double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public Double getPercDiscount() {
        return percDiscount;
    }

    public void setPercDiscount(Double percDiscount) {
        this.percDiscount = percDiscount;
    }

    public Double getCashDiscount() {
        return cashDiscount;
    }

    public void setCashDiscount(Double cashDiscount) {
        this.cashDiscount = cashDiscount;
    }

    public Double getInvoiceNetTotal() {
        return invoiceNetTotal;
    }

    public void setInvoiceNetTotal(Double invoiceNetTotal) {
        this.invoiceNetTotal = invoiceNetTotal;
    }

    public Double getInvoiceNetTotalTax() {
        return invoiceNetTotalTax;
    }

    public void setInvoiceNetTotalTax(Double invoiceNetTotalTax) {
        this.invoiceNetTotalTax = invoiceNetTotalTax;
    }

    public Boolean getIsRet() {
        return isRet;
    }

    public void setIsRet(Boolean isRet) {
        this.isRet = isRet;
    }

    public Integer getByUSer() {
        return byUSer;
    }

    public void setByUSer(Integer byUSer) {
        this.byUSer = byUSer;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public BigInteger getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigInteger exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getTaxDeduct() {
        return taxDeduct;
    }

    public void setTaxDeduct(Double taxDeduct) {
        this.taxDeduct = taxDeduct;
    }

    public Double getInvoiceNetTotalTaxDeduct() {
        return invoiceNetTotalTaxDeduct;
    }

    public void setInvoiceNetTotalTaxDeduct(Double invoiceNetTotalTaxDeduct) {
        this.invoiceNetTotalTaxDeduct = invoiceNetTotalTaxDeduct;
    }

    public Boolean getIsSuspended() {
        return isSuspended;
    }

    public void setIsSuspended(Boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    public List<SaleInvoiceDetails> getSaleInvoiceDetailsList() {
        return saleInvoiceDetailsList;
    }

    public void setSaleInvoiceDetailsList(List<SaleInvoiceDetails> saleInvoiceDetailsList) {
        this.saleInvoiceDetailsList = saleInvoiceDetailsList;
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
        if (!(object instanceof SaleInvoice)) {
            return false;
        }
        SaleInvoice other = (SaleInvoice) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.SaleInvoice[ id=" + id + " ]";
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public Names getCustomer() {
        return customer;
    }

    public void setCustomer(Names customer) {
        this.customer = customer;
    }

}
