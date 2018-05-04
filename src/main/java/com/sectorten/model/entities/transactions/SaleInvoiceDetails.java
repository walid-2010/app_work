/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sale_invoice_details")

public class SaleInvoiceDetails implements Serializable {

    @JoinColumn(name = "ItemId", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Items itemId;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;

    @Size(max = 25)
    @Column(name = "Code")
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private Double price;
    @Column(name = "Count")
    private BigDecimal count;
    @Column(name = "DiscountPercent")
    private Double discountPercent;
    @Column(name = "Value")
    private Double value;
    @Size(max = 250)
    @Column(name = "Description")
    private String description;
    @Size(max = 25)
    @Column(name = "Serial")
    private String serial;
    @Column(name = "ISBarCoded")
    private Boolean iSBarCoded;
    @Column(name = "NoOfNights")
    private Integer noOfNights;
    @JoinColumn(name = "InvoiceId", referencedColumnName = "ID")
    @ManyToOne
    private SaleInvoice invoiceId;

    public SaleInvoiceDetails() {
    }

    public SaleInvoiceDetails(Integer id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Boolean getISBarCoded() {
        return iSBarCoded;
    }

    public void setISBarCoded(Boolean iSBarCoded) {
        this.iSBarCoded = iSBarCoded;
    }

    public Integer getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(Integer noOfNights) {
        this.noOfNights = noOfNights;
    }

    public SaleInvoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(SaleInvoice invoiceId) {
        this.invoiceId = invoiceId;
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
        if (!(object instanceof SaleInvoiceDetails)) {
            return false;
        }
        SaleInvoiceDetails other = (SaleInvoiceDetails) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.SaleInvoiceDetails[ id=" + id + " ]";
    }

    public Items getItemId() {
        return itemId;
    }

    public void setItemId(Items itemId) {
        this.itemId = itemId;
    }

}
