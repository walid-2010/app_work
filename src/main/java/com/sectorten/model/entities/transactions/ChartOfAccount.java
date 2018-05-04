/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.entities.transactions;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "chart_of_account")

public class ChartOfAccount implements Serializable {

    @OneToMany(mappedBy = "accountID", fetch = FetchType.LAZY)
    private List<AccountHistory> accountHistoryList;
    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
    private List<ChartOfAccount> chartOfAccountList;
    @JoinColumn(name = "ParentId", referencedColumnName = "Account_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChartOfAccount parentId;
    @OneToMany(mappedBy = "acountID", fetch = FetchType.LAZY)
    private List<Jvdetailes> jvdetailesList;
    @OneToMany(mappedBy = "accountId", fetch = FetchType.LAZY)
    private List<Checkpayments> checkpaymentsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chartOfAccount", fetch = FetchType.LAZY)
    private List<ItemAccount> itemAccountList;
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "chart_of_of_account_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "chart_of_of_account_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Account_ID")
    private Integer accountID;
    @Size(max = 50)
    @Column(name = "Account_No")
    private String accountNo;
    @Size(max = 50)
    @Column(name = "Account_Name")
    private String accountName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Opening_Balance")
    private Double openingBalance;
    @Column(name = "Opening_Balance_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingBalanceDate;
    @Size(max = 100)
    @Column(name = "Note")
    private String note;
    @Column(name = "Last_Update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "alowdelete")
    private Boolean alowdelete;
    @Column(name = "adduser")
    private Integer adduser;
    @Column(name = "editeuser")
    private Integer editeuser;
    @Column(name = "editdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editdate;
    @Column(name = "adddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adddate;

    @Column(name = "TreeLevel")
    private Short treeLevel;
    @Column(name = "Current_Balance")
    private Double currentBalance;
    @Column(name = "Current_Balance_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currentBalanceDate;

    @Transient
    @Size(max = 50, message = "{field_size_exception}")
    private String parentCode = "", childCode = "";

    public ChartOfAccount() {
    }

    public ChartOfAccount(Integer accountID) {
        this.accountID = accountID;
    }

    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Date getOpeningBalanceDate() {
        return openingBalanceDate;
    }

    public void setOpeningBalanceDate(Date openingBalanceDate) {
        this.openingBalanceDate = openingBalanceDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Boolean getAlowdelete() {
        return alowdelete;
    }

    public void setAlowdelete(Boolean alowdelete) {
        this.alowdelete = alowdelete;
    }

    public Integer getAdduser() {
        return adduser;
    }

    public void setAdduser(Integer adduser) {
        this.adduser = adduser;
    }

    public Integer getEditeuser() {
        return editeuser;
    }

    public void setEditeuser(Integer editeuser) {
        this.editeuser = editeuser;
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public Short getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Short treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Date getCurrentBalanceDate() {
        return currentBalanceDate;
    }

    public void setCurrentBalanceDate(Date currentBalanceDate) {
        this.currentBalanceDate = currentBalanceDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountID != null ? accountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChartOfAccount)) {
            return false;
        }
        ChartOfAccount other = (ChartOfAccount) object;
        if ((this.accountID == null && other.accountID != null) || (this.accountID != null && !this.accountID.equals(other.accountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.ChartOfAccount[ accountID=" + accountID + " ]";
    }

    public List<AccountHistory> getAccountHistoryList() {
        return accountHistoryList;
    }

    public void setAccountHistoryList(List<AccountHistory> accountHistoryList) {
        this.accountHistoryList = accountHistoryList;
    }

    public List<ChartOfAccount> getChartOfAccountList() {
        return chartOfAccountList;
    }

    public void setChartOfAccountList(List<ChartOfAccount> chartOfAccountList) {
        this.chartOfAccountList = chartOfAccountList;
    }

    public ChartOfAccount getParentId() {
        return parentId;
    }

    public void setParentId(ChartOfAccount parentId) {
        this.parentId = parentId;
    }

    public List<Jvdetailes> getJvdetailesList() {
        return jvdetailesList;
    }

    public void setJvdetailesList(List<Jvdetailes> jvdetailesList) {
        this.jvdetailesList = jvdetailesList;
    }

    public List<Checkpayments> getCheckpaymentsList() {
        return checkpaymentsList;
    }

    public void setCheckpaymentsList(List<Checkpayments> checkpaymentsList) {
        this.checkpaymentsList = checkpaymentsList;
    }

    public List<ItemAccount> getItemAccountList() {
        return itemAccountList;
    }

    public void setItemAccountList(List<ItemAccount> itemAccountList) {
        this.itemAccountList = itemAccountList;
    }

}
