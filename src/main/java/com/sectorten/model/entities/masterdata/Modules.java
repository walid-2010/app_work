/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.masterdata;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbb
 */
@Entity
@Table(name = "modules")
@XmlRootElement

public class Modules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "path")
    private String path;
    @Size(max = 100)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "modules", fetch = FetchType.LAZY)
    private List<UserModules> userModules;

    @JoinColumn(name = "comp_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Components compId;
    @Size(max = 100)
    @Column(name = "form_name")
    private String formName;

    @Column(name = "non_menu")
    private boolean nonMenu;

    public Modules() {
    }

    public Modules(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isNonMenu() {
        return nonMenu;
    }

    public void setNonMenu(boolean nonMenu) {
        this.nonMenu = nonMenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserModules> getUserModules() {
        return userModules;
    }

    public void setUserModules(List<UserModules> userModules) {
        this.userModules = userModules;
    }

    public Components getCompId() {
        return compId;
    }

    public void setCompId(Components compId) {
        this.compId = compId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
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
        if (!(object instanceof Modules)) {
            return false;
        }
        Modules other = (Modules) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.security.masterData.Modules[ id=" + id + " ]";
    }

}
