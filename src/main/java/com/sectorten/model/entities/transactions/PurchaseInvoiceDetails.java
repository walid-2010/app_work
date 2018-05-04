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
@Table(name = "purchase_invoice_details")

public class PurchaseInvoiceDetails implements Serializable {

    @JoinColumn(name = "InvoiceId", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseInvoice invoiceId;
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
    @Column(name = "IsBarCoded")
    private Boolean isBarCoded;
    @Column(name = "roomNo")
    private Integer roomNo;

    public PurchaseInvoiceDetails() {
    }

    public PurchaseInvoiceDetails(Integer id) {
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

    public Boolean getIsBarCoded() {
        return isBarCoded;
    }

    public void setIsBarCoded(Boolean isBarCoded) {
        this.isBarCoded = isBarCoded;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
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
        if (!(object instanceof PurchaseInvoiceDetails)) {
            return false;
        }
        PurchaseInvoiceDetails other = (PurchaseInvoiceDetails) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.PurchaseInvoiceDetails[ id=" + id + " ]";
    }

    public void setInvoiceId(PurchaseInvoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Items getItemId() {
        return itemId;
    }

    public void setItemId(Items itemId) {
        this.itemId = itemId;
    }

}
