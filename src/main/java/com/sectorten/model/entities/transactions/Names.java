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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author al-kamel
 */
@Entity
@Table(name = "names")

public class Names implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nameID", fetch = FetchType.LAZY)
    private List<Contract> contractList;
    @OneToMany(mappedBy = "nameID", fetch = FetchType.LAZY)
    private List<Checks> checksList;
    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
    private List<PurchaseInvoice> purchaseInvoiceList;
    @OneToMany(mappedBy = "tocust", fetch = FetchType.LAZY)
    private List<PurchaseInvoice> purchaseInvoiceList1;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<SaleInvoice> saleInvoiceList;
    @JoinColumn(name = "Customer_type", referencedColumnName = "Customer_Type_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customertype customertype;
    @OneToMany(mappedBy = "salesHead", fetch = FetchType.LAZY)
    private List<Names> namesList;
    @JoinColumn(name = "SalesHead", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names salesHead;
    @OneToMany(mappedBy = "mandoob", fetch = FetchType.LAZY)
    private List<Names> namesList1;
    @OneToMany(mappedBy = "salesPerson", fetch = FetchType.LAZY)
    private List<Names> namesList2;
    @JoinColumn(name = "SalesPerson", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names salesPerson;
    @OneToMany(mappedBy = "mandoobHead", fetch = FetchType.LAZY)
    private List<Names> namesList3;
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "names_id_seq", allocationSize = 1, table = "seq_table",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value")
    @GeneratedValue(generator = "names_id_seq", strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "Name_ID")
    private Integer nameID;
    @Size(max = 50)
    @Column(name = "Name_No")
    private String nameNo;
    @Size(max = 50)
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Credit_Limit")
    private Double creditLimit;
    @Column(name = "Opening_Balance")
    private Double openingBalance;
    @Column(name = "Opening_Balance_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingBalanceDate;
    @Column(name = "DefaultCustomer")
    private Boolean defaultCustomer;
    @Column(name = "Freeze_Price")
    private Boolean freezePrice;
    @Size(max = 255)
    @Column(name = "Pricing")
    private String pricing;
    @Column(name = "Customer")
    private Integer customer;
    @Column(name = "Supplier")
    private Integer supplier;
    @JoinColumn(name = "mandob", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names mandob;
    @Column(name = "Seller")
    private Integer seller;
    @Column(name = "Last_Update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Column(name = "adduser")
    private Integer adduser;
    @Column(name = "editeuser")
    private Integer editeuser;
    @Column(name = "adddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adddate;
    @Size(max = 100)
    @Column(name = "note")
    private String note;
    @Column(name = "Active")
    private Boolean active;
    @Column(name = "AccountID")
    private Integer accountID;
    @Column(name = "Auto")
    private Boolean auto;

    @JoinColumn(name = "nationality", referencedColumnName = "Nationality_ID")
    @ManyToOne(fetch = FetchType.LAZY)

    private Nationality nationality;
    @Size(max = 50)
    @Column(name = "BankAccount")
    private String bankAccount;

    @JoinColumn(name = "mandoobHead", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names mandoobHead;

    @JoinColumn(name = "mandoob", referencedColumnName = "Name_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Names mandoob;

    @JoinColumn(name = "manfaz", referencedColumnName = "mnfaz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Manfaz manfaz;
    @Column(name = "Discount")
    private Double discount;
    @OneToMany(mappedBy = "nameID")
    private List<NameDetails> nameDetailsList;

    public Names() {
    }

    public Names(Integer nameID) {
        this.nameID = nameID;
    }

    public Integer getNameID() {
        return nameID;
    }

    public void setNameID(Integer nameID) {
        this.nameID = nameID;
    }

    public String getNameNo() {
        return nameNo;
    }

    public void setNameNo(String nameNo) {
        this.nameNo = nameNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
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

    public Boolean getDefaultCustomer() {
        return defaultCustomer;
    }

    public void setDefaultCustomer(Boolean defaultCustomer) {
        this.defaultCustomer = defaultCustomer;
    }

    public Boolean getFreezePrice() {
        return freezePrice;
    }

    public void setFreezePrice(Boolean freezePrice) {
        this.freezePrice = freezePrice;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Integer getSupplier() {
        return supplier;
    }

    public void setSupplier(Integer supplier) {
        this.supplier = supplier;
    }

    public Integer getSeller() {
        return seller;
    }

    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Names getMandob() {
        return mandob;
    }

    public void setMandob(Names mandob) {
        this.mandob = mandob;
    }

    public Names getMandoobHead() {
        return mandoobHead;
    }

    public void setMandoobHead(Names mandoobHead) {
        this.mandoobHead = mandoobHead;
    }

    public Names getMandoob() {
        return mandoob;
    }

    public void setMandoob(Names mandoob) {
        this.mandoob = mandoob;
    }

    public Manfaz getManfaz() {
        return manfaz;
    }

    public void setManfaz(Manfaz manfaz) {
        this.manfaz = manfaz;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<NameDetails> getNameDetailsList() {
        return nameDetailsList;
    }

    public void setNameDetailsList(List<NameDetails> nameDetailsList) {
        this.nameDetailsList = nameDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameID != null ? nameID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Names)) {
            return false;
        }
        Names other = (Names) object;
        if ((this.nameID == null && other.nameID != null) || (this.nameID != null && !this.nameID.equals(other.nameID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sectorten.model.entities.transactions.Names[ nameID=" + nameID + " ]";
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public List<Checks> getChecksList() {
        return checksList;
    }

    public void setChecksList(List<Checks> checksList) {
        this.checksList = checksList;
    }

    public List<PurchaseInvoice> getPurchaseInvoiceList() {
        return purchaseInvoiceList;
    }

    public void setPurchaseInvoiceList(List<PurchaseInvoice> purchaseInvoiceList) {
        this.purchaseInvoiceList = purchaseInvoiceList;
    }

    public List<PurchaseInvoice> getPurchaseInvoiceList1() {
        return purchaseInvoiceList1;
    }

    public void setPurchaseInvoiceList1(List<PurchaseInvoice> purchaseInvoiceList1) {
        this.purchaseInvoiceList1 = purchaseInvoiceList1;
    }

    public List<SaleInvoice> getSaleInvoiceList() {
        return saleInvoiceList;
    }

    public void setSaleInvoiceList(List<SaleInvoice> saleInvoiceList) {
        this.saleInvoiceList = saleInvoiceList;
    }

    public Customertype getCustomertype() {
        return customertype;
    }

    public void setCustomertype(Customertype customertype) {
        this.customertype = customertype;
    }

    public List<Names> getNamesList() {
        return namesList;
    }

    public void setNamesList(List<Names> namesList) {
        this.namesList = namesList;
    }

    public Names getSalesHead() {
        return salesHead;
    }

    public void setSalesHead(Names salesHead) {
        this.salesHead = salesHead;
    }

    public List<Names> getNamesList1() {
        return namesList1;
    }

    public void setNamesList1(List<Names> namesList1) {
        this.namesList1 = namesList1;
    }

    public List<Names> getNamesList2() {
        return namesList2;
    }

    public void setNamesList2(List<Names> namesList2) {
        this.namesList2 = namesList2;
    }

    public Names getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(Names salesPerson) {
        this.salesPerson = salesPerson;
    }

    public List<Names> getNamesList3() {
        return namesList3;
    }

    public void setNamesList3(List<Names> namesList3) {
        this.namesList3 = namesList3;
    }

}
