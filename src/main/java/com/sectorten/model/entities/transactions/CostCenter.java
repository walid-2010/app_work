/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "cost_center")

public class CostCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "cost_center_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "cost_center_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "CostCenter")
    private String costCenter;
    @Size(max = 50)
    @Column(name = "Code")
    private String code;

    @JoinColumn(name = "ParentId", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CostCenter parentId;

    @Transient
    @Size(max = 50, message = "{field_size_exception}")
    private String parentCode = "", childCode = "";

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "parentId", fetch = FetchType.LAZY)
    private List<CostCenter> groupChildrenList;

    public CostCenter() {
    }

    public CostCenter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CostCenter getParentId() {
        return parentId;
    }

    public void setParentId(CostCenter parentId) {
        this.parentId = parentId;
    }

    public List<CostCenter> getGroupChildrenList() {
        return groupChildrenList;
    }

    public void setGroupChildrenList(List<CostCenter> groupChildrenList) {
        this.groupChildrenList = groupChildrenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostCenter)) {
            return false;
        }
        CostCenter other = (CostCenter) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.CostCenter[ id=" + id + " ]";
    }

}
