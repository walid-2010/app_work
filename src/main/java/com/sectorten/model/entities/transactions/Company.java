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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "company")

public class Company implements Serializable {

    @Lob
    @Column(name = "CMP_logo")
    private byte[] cMPlogo;

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "company_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "company_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMP1")
    private Integer cmp1;
    @Size(max = 255)
    @Column(name = "CMP_name")
    private String cMPname;
    @Size(max = 255)
    @Column(name = "CMP_address")
    private String cMPaddress;
    @Size(max = 255)
    @Column(name = "CMP_tel")
    private String cMPtel;
    @Size(max = 255)
    @Column(name = "CMP_fax")
    private String cMPfax;
    @Size(max = 255)
    @Column(name = "CMP_email")
    private String cMPemail;
    @Size(max = 255)
    @Column(name = "CMP_site")
    private String cMPsite;
    @Size(max = 255)
    @Column(name = "CMP_city")
    private String cMPcity;
    @Size(max = 255)
    @Column(name = "CMP_postel")
    private String cMPpostel;
    @Size(max = 255)
    @Column(name = "CMP_mobile")
    private String cMPmobile;
    @Size(max = 255)
    @Column(name = "CMP_gov")
    private String cMPgov;
    @Size(max = 255)
    @Column(name = "CMP_cunt")
    private String cMPcunt;

    public Company() {
    }

    public Company(Integer cmp1) {
        this.cmp1 = cmp1;
    }

    public Integer getCmp1() {
        return cmp1;
    }

    public void setCmp1(Integer cmp1) {
        this.cmp1 = cmp1;
    }

    public String getCMPname() {
        return cMPname;
    }

    public void setCMPname(String cMPname) {
        this.cMPname = cMPname;
    }

    public String getCMPaddress() {
        return cMPaddress;
    }

    public void setCMPaddress(String cMPaddress) {
        this.cMPaddress = cMPaddress;
    }

    public String getCMPtel() {
        return cMPtel;
    }

    public void setCMPtel(String cMPtel) {
        this.cMPtel = cMPtel;
    }

    public String getCMPfax() {
        return cMPfax;
    }

    public void setCMPfax(String cMPfax) {
        this.cMPfax = cMPfax;
    }

    public String getCMPemail() {
        return cMPemail;
    }

    public void setCMPemail(String cMPemail) {
        this.cMPemail = cMPemail;
    }

    public String getcMPname() {
        return cMPname;
    }

    public void setcMPname(String cMPname) {
        this.cMPname = cMPname;
    }

    public String getcMPaddress() {
        return cMPaddress;
    }

    public void setcMPaddress(String cMPaddress) {
        this.cMPaddress = cMPaddress;
    }

    public String getcMPtel() {
        return cMPtel;
    }

    public void setcMPtel(String cMPtel) {
        this.cMPtel = cMPtel;
    }

    public String getcMPfax() {
        return cMPfax;
    }

    public void setcMPfax(String cMPfax) {
        this.cMPfax = cMPfax;
    }

    public String getcMPemail() {
        return cMPemail;
    }

    public void setcMPemail(String cMPemail) {
        this.cMPemail = cMPemail;
    }

    public byte[] getcMPlogo() {
        return cMPlogo;
    }

    public void setcMPlogo(byte[] cMPlogo) {
        this.cMPlogo = cMPlogo;
    }

    public String getcMPsite() {
        return cMPsite;
    }

    public void setcMPsite(String cMPsite) {
        this.cMPsite = cMPsite;
    }

    public String getcMPcity() {
        return cMPcity;
    }

    public void setcMPcity(String cMPcity) {
        this.cMPcity = cMPcity;
    }

    public String getcMPpostel() {
        return cMPpostel;
    }

    public void setcMPpostel(String cMPpostel) {
        this.cMPpostel = cMPpostel;
    }

    public String getcMPmobile() {
        return cMPmobile;
    }

    public void setcMPmobile(String cMPmobile) {
        this.cMPmobile = cMPmobile;
    }

    public String getcMPgov() {
        return cMPgov;
    }

    public void setcMPgov(String cMPgov) {
        this.cMPgov = cMPgov;
    }

    public String getcMPcunt() {
        return cMPcunt;
    }

    public void setcMPcunt(String cMPcunt) {
        this.cMPcunt = cMPcunt;
    }

    public String getCMPsite() {
        return cMPsite;
    }

    public void setCMPsite(String cMPsite) {
        this.cMPsite = cMPsite;
    }

    public String getCMPcity() {
        return cMPcity;
    }

    public void setCMPcity(String cMPcity) {
        this.cMPcity = cMPcity;
    }

    public String getCMPpostel() {
        return cMPpostel;
    }

    public void setCMPpostel(String cMPpostel) {
        this.cMPpostel = cMPpostel;
    }

    public String getCMPmobile() {
        return cMPmobile;
    }

    public void setCMPmobile(String cMPmobile) {
        this.cMPmobile = cMPmobile;
    }

    public String getCMPgov() {
        return cMPgov;
    }

    public void setCMPgov(String cMPgov) {
        this.cMPgov = cMPgov;
    }

    public String getCMPcunt() {
        return cMPcunt;
    }

    public void setCMPcunt(String cMPcunt) {
        this.cMPcunt = cMPcunt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmp1 != null ? cmp1.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.cmp1 == null && other.cmp1 != null) || (this.cmp1 != null && !this.cmp1.equals(other.cmp1))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Company[ cmp1=" + cmp1 + " ]";
    }

    public byte[] getCMPlogo() {
        return cMPlogo;
    }

    public void setCMPlogo(byte[] cMPlogo) {
        this.cMPlogo = cMPlogo;
    }

}
