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
import javax.persistence.Lob;
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
@Table(name = "hotel")

public class Hotel implements Serializable {

    @OneToMany(mappedBy = "hotelID", fetch = FetchType.LAZY)
    private List<PurchaseInvoice> purchaseInvoiceList;
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "hotel_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "hotel_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "HotelID")
    private Integer hotelID;
    @Size(max = 50)
    @Column(name = "HotelName")
    private String hotelName;
    @Size(max = 50)
    @Column(name = "Tel")
    private String tel;
    @Size(max = 50)
    @Column(name = "AddTel")
    private String addTel;
    @Size(max = 50)
    @Column(name = "Mobile")
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Fax")
    private String fax;
    @Lob
    @Size(max = 65535)
    @Column(name = "Address")
    private String address;

    public Hotel() {
    }

    public Hotel(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddTel() {
        return addTel;
    }

    public void setAddTel(String addTel) {
        this.addTel = addTel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelID != null ? hotelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.hotelID == null && other.hotelID != null) || (this.hotelID != null && !this.hotelID.equals(other.hotelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Hotel[ hotelID=" + hotelID + " ]";
    }

    public List<PurchaseInvoice> getPurchaseInvoiceList() {
        return purchaseInvoiceList;
    }

    public void setPurchaseInvoiceList(List<PurchaseInvoice> purchaseInvoiceList) {
        this.purchaseInvoiceList = purchaseInvoiceList;
    }

}
