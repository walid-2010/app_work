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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "purchase_invoice")

public class PurchaseInvoice implements Serializable {

    @OneToMany(mappedBy = "invoiceId", fetch = FetchType.LAZY)
    private List<PurchaseInvoiceDetails> purchaseInvoiceDetailsList;
    @JoinColumn(name = "CostCenter", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CostCenter costCenter;
    @JoinColumn(name = "HotelID", referencedColumnName = "HotelID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotelID;
    @JoinColumn(name = "Tocust", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names tocust;
    @JoinColumn(name = "vendor", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names vendor;

    @JoinColumn(name = "Country", referencedColumnName = "Nationality_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nationality country;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @TableGenerator(name = "purchaseInvoice_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "purchaseInvoice_id_seq", strategy = GenerationType.TABLE)
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
    @Column(name = "ToStore")
    private Integer toStore;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "InvoiceTotal")
    private Double invoiceTotal;
    @Column(name = "PercDiscount")
    private Double percDiscount;
    @Column(name = "CashDiscount")
    private Double cashDiscount;
    @Column(name = "InvoiceNetTotal")
    private Double invoiceNetTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISRet")
    private boolean iSRet;
    @Column(name = "ByUser")
    private Integer byUser;
    @Column(name = "InvoiceNetTotalTax")
    private Double invoiceNetTotalTax;
    @Column(name = "modifier")
    private Integer modifier;
    @Column(name = "ExchangeRate")
    private Double exchangeRate;

    @Column(name = "TaxDeduct")
    private Double taxDeduct;
    @Column(name = "InvoiceNetTotalTaxDeduct")
    private Double invoiceNetTotalTaxDeduct;

    @Size(max = 50)
    @Column(name = "ProviderName")
    private String providerName;

    @Size(max = 50)
    @Column(name = "enzalStuts")
    private String enzalStuts;

    public PurchaseInvoice() {
    }

    public PurchaseInvoice(Integer id) {
        this.id = id;
    }

    public PurchaseInvoice(Integer id, boolean iSRet) {
        this.id = id;
        this.iSRet = iSRet;
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

    public Integer getToStore() {
        return toStore;
    }

    public void setToStore(Integer toStore) {
        this.toStore = toStore;
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

    public boolean getISRet() {
        return iSRet;
    }

    public void setISRet(boolean iSRet) {
        this.iSRet = iSRet;
    }

    public Integer getByUser() {
        return byUser;
    }

    public void setByUser(Integer byUser) {
        this.byUser = byUser;
    }

    public Double getInvoiceNetTotalTax() {
        return invoiceNetTotalTax;
    }

    public void setInvoiceNetTotalTax(Double invoiceNetTotalTax) {
        this.invoiceNetTotalTax = invoiceNetTotalTax;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Names getVendor() {
        return vendor;
    }

    public void setVendor(Names vendor) {
        this.vendor = vendor;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getEnzalStuts() {
        return enzalStuts;
    }

    public void setEnzalStuts(String enzalStuts) {
        this.enzalStuts = enzalStuts;
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
        if (!(object instanceof PurchaseInvoice)) {
            return false;
        }
        PurchaseInvoice other = (PurchaseInvoice) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.PurchaseInvoice[ id=" + id + " ]";
    }

    public List<PurchaseInvoiceDetails> getPurchaseInvoiceDetailsList() {
        return purchaseInvoiceDetailsList;
    }

    public void setPurchaseInvoiceDetailsList(List<PurchaseInvoiceDetails> purchaseInvoiceDetailsList) {
        this.purchaseInvoiceDetailsList = purchaseInvoiceDetailsList;
    }

    public CostCenter getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenter costCenter) {
        this.costCenter = costCenter;
    }

    public Hotel getHotelID() {
        return hotelID;
    }

    public void setHotelID(Hotel hotelID) {
        this.hotelID = hotelID;
    }

    public Names getTocust() {
        return tocust;
    }

    public void setTocust(Names tocust) {
        this.tocust = tocust;
    }

    public Nationality getCountry() {
        return country;
    }

    public void setCountry(Nationality country) {
        this.country = country;
    }

}
