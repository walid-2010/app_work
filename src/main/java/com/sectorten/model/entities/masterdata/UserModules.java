/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.masterdata;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nbb
 */
@Entity
@Table(name = "user_modules")
@XmlRootElement

public class UserModules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @TableGenerator(name = "user_modules_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "user_modules_id_seq", strategy = GenerationType.TABLE)

    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "module_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modules modules;
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ModulesOperations operationId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Transient
    private Components componentId;

    public UserModules() {
    }

    public UserModules(Long id) {
        this.id = id;
    }

    public UserModules(Long id, User user, Modules modules) {
        this.id = id;
        this.user = user;
        this.modules = modules;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    public ModulesOperations getOperationId() {
        return operationId;
    }

    public void setOperationId(ModulesOperations operationId) {
        this.operationId = operationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof UserModules)) {
            return false;
        }
        UserModules other = (UserModules) object;
        if ((this.id == null && other.getId()!= null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Components getComponentId() {

        if (componentId == null && modules != null) {
            componentId = modules.getCompId();
        }
        return componentId;
    }

    public void setComponentId(Components componentId) {
        this.componentId = componentId;
    }

    @Override
    public String toString() {
        return "com.security.masterData.UserModules[ id=" + id + " ]";
    }

}
