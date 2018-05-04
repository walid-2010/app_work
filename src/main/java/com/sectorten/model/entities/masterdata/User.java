/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.masterdata;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author nbb
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.searchUser",
            query = "SELECT s "
            + "FROM User s "
            + " WHERE s.username = :USER_NAME ")})
public class User implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "isAdmin")
    private boolean isAdmin;

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "user_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;

    @Size(min = 1, max = 100)
    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @Size(max = 100)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 100)
    @Column(name = "secondName")
    private String secondName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserComponents> userComponents;
    @PrivateOwned
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserModules> userModules;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<UserRoles> userRolesList;

    @Transient
    private String PassWordConfirmed;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        String fName = firstName != null ? firstName : "";
        String sName = secondName != null ? secondName : "";
        String lName = lastName != null ? lastName : "";
        return fName + " " + sName + " " + lName;
    }

    public String getPassWordConfirmed() {
        return PassWordConfirmed;
    }

    public void setPassWordConfirmed(String PassWordConfirmed) {
        this.PassWordConfirmed = PassWordConfirmed;
    }

    public List<UserRoles> getUserRolesList() {
        return userRolesList;
    }

    public void setUserRolesList(List<UserRoles> userRolesList) {
        this.userRolesList = userRolesList;
    }

    public List<UserComponents> getUserComponents() {
        return userComponents;
    }

    public void setUserComponents(List<UserComponents> userComponents) {
        this.userComponents = userComponents;
    }

    public List<UserModules> getUserModules() {
        return userModules;
    }

    public void setUserModules(List<UserModules> userModules) {
        this.userModules = userModules;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.getId()!= null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.masterdata.User[ id=" + id + " ]";
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
