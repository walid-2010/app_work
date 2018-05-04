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
@Table(name = "manfaz")

public class Manfaz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "manfez_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "manfez_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "mnfaz_id")
    private Integer mnfazId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "manfaz_name")
    private String manfazName;

    public Manfaz() {
    }

    public Manfaz(Integer mnfazId) {
        this.mnfazId = mnfazId;
    }

    public Manfaz(Integer mnfazId, String manfazName) {
        this.mnfazId = mnfazId;
        this.manfazName = manfazName;
    }

    public Integer getMnfazId() {
        return mnfazId;
    }

    public void setMnfazId(Integer mnfazId) {
        this.mnfazId = mnfazId;
    }

    public String getManfazName() {
        return manfazName;
    }

    public void setManfazName(String manfazName) {
        this.manfazName = manfazName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mnfazId != null ? mnfazId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manfaz)) {
            return false;
        }
        Manfaz other = (Manfaz) object;
        if ((this.mnfazId == null && other.mnfazId != null) || (this.mnfazId != null && !this.mnfazId.equals(other.mnfazId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Manfaz[ mnfazId=" + mnfazId + " ]";
    }

}
