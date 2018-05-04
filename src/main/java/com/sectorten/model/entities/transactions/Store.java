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
@Table(name = "store")

public class Store implements Serializable {

    @OneToMany(mappedBy = "stroeId", fetch = FetchType.LAZY)
    private List<ItemsStorage> itemsStorageList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "store_id")
    @TableGenerator(name = "store_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "store_id_seq", strategy = GenerationType.TABLE)
    private Integer storeId;
    @Size(max = 50)
    @Column(name = "store_no")
    private String storeNo;
    @Size(max = 100)
    @Column(name = "store_name")
    private String storeName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 25)
    @Column(name = "phone")
    private String phone;
    @Size(max = 25)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 25)
    @Column(name = "phone2")
    private String phone2;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 25)
    @Column(name = "fax")
    private String fax;
    @Size(max = 100)
    @Column(name = "address")
    private String address;
    @Column(name = "block")
    private Boolean block;
    @Column(name = "IsBrach")
    private Boolean isBrach;
    @Column(name = "AllowStoreSale")
    private Boolean allowStoreSale;
    @Column(name = "IsDefault")
    private Boolean isDefault;

    public Store() {
    }

    public Store(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Boolean getIsBrach() {
        return isBrach;
    }

    public void setIsBrach(Boolean isBrach) {
        this.isBrach = isBrach;
    }

    public Boolean getAllowStoreSale() {
        return allowStoreSale;
    }

    public void setAllowStoreSale(Boolean allowStoreSale) {
        this.allowStoreSale = allowStoreSale;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Store[ storeId=" + storeId + " ]";
    }

    public List<ItemsStorage> getItemsStorageList() {
        return itemsStorageList;
    }

    public void setItemsStorageList(List<ItemsStorage> itemsStorageList) {
        this.itemsStorageList = itemsStorageList;
    }

}
