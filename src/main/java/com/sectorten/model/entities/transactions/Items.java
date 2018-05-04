/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "items")

public class Items implements Serializable {

    @OneToMany(mappedBy = "itemId", fetch = FetchType.LAZY)
    private List<PurchaseInvoiceDetails> purchaseInvoiceDetailsList;
    @OneToMany(mappedBy = "itemId", fetch = FetchType.LAZY)
    private List<ItemsStorage> itemsStorageList;
    @OneToMany(mappedBy = "itemId", fetch = FetchType.LAZY)
    private List<StockTransDetails> stockTransDetailsList;
    @OneToMany(mappedBy = "itemId", fetch = FetchType.LAZY)
    private List<SaleInvoiceDetails> saleInvoiceDetailsList;
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "items_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "items_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Size(max = 35)
    @Column(name = "Code")
    private String code;
    @Column(name = "IsService")
    private Boolean isService;
    @Size(max = 100)
    @Column(name = "Name")
    private String name;
    @Size(max = 400)
    @Column(name = "Note")
    private String note;
    @Column(name = "IsSerialieze")
    private Boolean isSerialieze;
    @Column(name = "MinLimit")
    private Integer minLimit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Mass_sale")
    private Double masssale;
    @Column(name = "Customer_Sale")
    private Double customerSale;
    @Column(name = "Purchase_Price")
    private Double purchasePrice;
    @Column(name = "LimitSale_Price")
    private Double limitSalePrice;
    @Column(name = "Catagory")
    private Integer catagory;
    @Column(name = "Active")
    private Boolean active;
    @Size(max = 255)
    @Column(name = "ItemPhoto")
    private String itemPhoto;
    @Column(name = "Auto")
    private Boolean auto;
    @Size(max = 50)
    @Column(name = "FacCode")
    private String facCode;
    @Column(name = "Price1")
    private Double price1;
    @Column(name = "Price2")
    private Double price2;
    @Column(name = "Price3")
    private Double price3;
    @Column(name = "Price4")
    private Double price4;
    @OneToMany(mappedBy = "itemId")
    private List<SerializedItems> serializedItemsList;

    public Items() {
    }

    public Items(Integer id) {
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

    public Boolean getIsService() {
        return isService;
    }

    public void setIsService(Boolean isService) {
        this.isService = isService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsSerialieze() {
        return isSerialieze;
    }

    public void setIsSerialieze(Boolean isSerialieze) {
        this.isSerialieze = isSerialieze;
    }

    public Integer getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(Integer minLimit) {
        this.minLimit = minLimit;
    }

    public Double getMasssale() {
        return masssale;
    }

    public void setMasssale(Double masssale) {
        this.masssale = masssale;
    }

    public Double getCustomerSale() {
        return customerSale;
    }

    public void setCustomerSale(Double customerSale) {
        this.customerSale = customerSale;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getLimitSalePrice() {
        return limitSalePrice;
    }

    public void setLimitSalePrice(Double limitSalePrice) {
        this.limitSalePrice = limitSalePrice;
    }

    public Integer getCatagory() {
        return catagory;
    }

    public void setCatagory(Integer catagory) {
        this.catagory = catagory;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    public String getFacCode() {
        return facCode;
    }

    public void setFacCode(String facCode) {
        this.facCode = facCode;
    }

    public Double getPrice1() {
        return price1;
    }

    public void setPrice1(Double price1) {
        this.price1 = price1;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public Double getPrice3() {
        return price3;
    }

    public void setPrice3(Double price3) {
        this.price3 = price3;
    }

    public Double getPrice4() {
        return price4;
    }

    public void setPrice4(Double price4) {
        this.price4 = price4;
    }

    public List<SerializedItems> getSerializedItemsList() {
        return serializedItemsList;
    }

    public void setSerializedItemsList(List<SerializedItems> serializedItemsList) {
        this.serializedItemsList = serializedItemsList;
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
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Items[ id=" + id + " ]";
    }

    public List<PurchaseInvoiceDetails> getPurchaseInvoiceDetailsList() {
        return purchaseInvoiceDetailsList;
    }

    public void setPurchaseInvoiceDetailsList(List<PurchaseInvoiceDetails> purchaseInvoiceDetailsList) {
        this.purchaseInvoiceDetailsList = purchaseInvoiceDetailsList;
    }

    public List<ItemsStorage> getItemsStorageList() {
        return itemsStorageList;
    }

    public void setItemsStorageList(List<ItemsStorage> itemsStorageList) {
        this.itemsStorageList = itemsStorageList;
    }

    public List<StockTransDetails> getStockTransDetailsList() {
        return stockTransDetailsList;
    }

    public void setStockTransDetailsList(List<StockTransDetails> stockTransDetailsList) {
        this.stockTransDetailsList = stockTransDetailsList;
    }

    public List<SaleInvoiceDetails> getSaleInvoiceDetailsList() {
        return saleInvoiceDetailsList;
    }

    public void setSaleInvoiceDetailsList(List<SaleInvoiceDetails> saleInvoiceDetailsList) {
        this.saleInvoiceDetailsList = saleInvoiceDetailsList;
    }

}
