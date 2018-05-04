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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "package")

public class Package implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "package_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "package_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pakage_ID")
    private Integer pakageID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Pakage_name")
    private String pakagename;
    @Column(name = "years")
    private Integer years;
    @Column(name = "persons")
    private Integer persons;

    public Package() {
    }

    public Package(Integer pakageID) {
        this.pakageID = pakageID;
    }

    public Package(Integer pakageID, String pakagename) {
        this.pakageID = pakageID;
        this.pakagename = pakagename;
    }

    public Integer getPakageID() {
        return pakageID;
    }

    public void setPakageID(Integer pakageID) {
        this.pakageID = pakageID;
    }

    public String getPakagename() {
        return pakagename;
    }

    public void setPakagename(String pakagename) {
        this.pakagename = pakagename;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pakageID != null ? pakageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Package)) {
            return false;
        }
        Package other = (Package) object;
        if ((this.pakageID == null && other.pakageID != null) || (this.pakageID != null && !this.pakageID.equals(other.pakageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Package[ pakageID=" + pakageID + " ]";
    }

}
