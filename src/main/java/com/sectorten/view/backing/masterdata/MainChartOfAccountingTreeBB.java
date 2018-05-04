/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.masterdata;

import com.sectorten.business.services.transactions.IMasterDataServices;
import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.transactions.ChartOfAccountDao;
import com.sectorten.model.entities.transactions.ChartOfAccount;
import com.sectorten.model.exception.BusinessLogicViolationException;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.BaseBackBean;
import com.sectorten.view.common.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.util.ObjectUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named(value = "mainChartOfAccountingTreeBB")
@ViewScoped
public class MainChartOfAccountingTreeBB extends BaseBackBean implements Serializable {

    // <editor-fold defaultstate="collapsed" desc="Daos">
    @Inject
    private IMasterDataServices masterDataServices;

    @Inject
    private ChartOfAccountDao chartOfAccountDao;

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Properties">
    private TreeNode root, selectedNode;
    private ChartOfAccount selectedAccount;
    private Boolean renderTableView = Boolean.TRUE;
    private String chartOfAccount = "chartOfAccount";
    private BigDecimal openningBalance = BigDecimal.ZERO;
    private Date openinningBalanceDate;

    //</editor-fold>
    /**
     * Creates a new instance of MainChartOfAccountingTreeBB
     */
    public MainChartOfAccountingTreeBB() {
    }

    // <editor-fold defaultstate="collapsed" desc="Initialization">
    @PostConstruct
    void init() {

        List<ChartOfAccount> chartAccountList = null;
        try {
            chartAccountList = chartOfAccountDao.findAllOrderedById();
        } catch (Exception ex) {
            Logger.getLogger(MainChartOfAccountingTreeBB.class.getName()).log(Level.SEVERE, null, ex);
        }

//        root = this.buildTreeRecursive(null, chartAccountList);
        root = this.buildAccountsTree(chartAccountList);

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Build Accounts Tree">
    private TreeNode buildAccountsTree(List<ChartOfAccount> chartAccountList) {

        TreeNode rootNode, node;
        try {

            rootNode = new DefaultTreeNode("root", "chartOfAccount", null);

            List<TreeNode> tempList;
            List<TreeNode> accountNodeChildren = rootNode.getChildren();
            Iterator<ChartOfAccount> fnAccIter = chartAccountList.iterator();

            //1-select nodes where parent id == null ,add to root ,remove from main list
            while (fnAccIter.hasNext()) {
                ChartOfAccount chartAccount = fnAccIter.next();
                if (ObjectUtils.equals(chartAccount.getParentId(), null)) {
                    node = new DefaultTreeNode("child", chartAccount, null);
                    accountNodeChildren.add(node);

                    fnAccIter.remove();
                }
            }
            //2-Find parent child
            while (!accountNodeChildren.isEmpty()) {
                tempList = new ArrayList<>();
                for (TreeNode parent : accountNodeChildren) {
                    Iterator<ChartOfAccount> modifiedIter = chartAccountList.iterator();
                    while (modifiedIter.hasNext()) {
                        ChartOfAccount child = modifiedIter.next();
                        if (Objects.equals((parent.getData()), child.getParentId())) {
                            node = new DefaultTreeNode("child", child, null);
                            parent.getChildren().add(node);
                            tempList.add(node);
                            modifiedIter.remove();
                        }
                    }
                }
                accountNodeChildren = tempList;
            }
            return rootNode;
        } catch (Exception exception) {
            Logger.getLogger(MainChartOfAccountingTreeBB.class.getName()).log(Level.SEVERE, null, exception);
        }

        return null;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Commented Build Recursive Method">
//    private TreeNode buildTreeRecursive(TreeNode recParent, List<ChartOfAccount> accountsList) {
//
//        TreeNode parentNode;
//        ChartOfAccount chartAccount;
//        Iterator<ChartOfAccount> iter = accountsList.iterator(); // to avoid concurrent fnAccess to the list
//
//        if (recParent != null) {
//            parentNode = recParent;
//
//            while (iter.hasNext()) {
//                chartAccount = iter.next(); // move to next item in list iterator
//                if (chartAccount.getAccParentId() != null && Objects.equals(chartAccount.getAccParentId().getId(), ((ChartOfAccount) parentNode.getData()).getId())) {
////                    iter.remove();
//                    TreeNode addedNode = new DefaultTreeNode("child", chartAccount, null);
//                    parentNode.getChildren().add(addedNode);
//
//                    this.buildTreeRecursive(addedNode, accountsList);
//
//                }
//
//            }
//
//            return null;
//
//        } else {
//            parentNode = new DefaultTreeNode("root", chartOfAccount, null);
//            parentNode.setExpanded(true);
//
//            while (iter.hasNext()) {
//                chartAccount = iter.next(); // move to next item in list iterator
//                if (Objects.equals(chartAccount.getAccParentId(), null)) {
//                    TreeNode addedNode = new DefaultTreeNode("child", chartAccount, null);
//                    parentNode.getChildren().add(addedNode);
//
//                    setLocalDebitSum(localDebitSum.add(chartAccount.getDtLcCuml()));
//                    setLocalCreditSum(localCreditSum.add(chartAccount.getCrLcCuml()));
//                    setLocalBalance(localBalance.add(chartAccount.getTlClCuml()));
//
//                    iter.remove();
//                    this.buildTreeRecursive(addedNode, accountsList);
//                }
//            }
//
//            return parentNode;
//
//        }
//    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Edit Account">
    public String editAccount() {

        // if account is selected and not chart root point
        if (this.selectedNode != null && !this.selectedNode.getData().toString().equals(chartOfAccount)) {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .put("accountId", ((ChartOfAccount) this.selectedNode.getData()).getAccountID());
            putEditedObject((ChartOfAccount) selectedNode.getData());
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .put("redirectFlag", ConstantStrings.EDIT);
            putAddEditMode(ConstantStrings.EDIT);
            return "AddEdit";
        } else {

        }

        return "";
    }
    //</editor-fold>

    public String createAccount() {
        String retURL = "AddEdit";

        // if account is selected and not chart root point
        try {
            if (this.selectedNode != null && !this.selectedNode.getData().toString().equals(chartOfAccount)) {
                ChartOfAccount fnAcc = (ChartOfAccount) this.selectedNode.getData();

                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getFlash()
                        .put("accountId", fnAcc.getAccountID());
            }

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash()
                    .put("redirectFlag", ConstantStrings.ADD);
            putAddEditMode(ConstantStrings.ADD);
        } catch (BusinessLogicViolationException BLVException) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(BLVException));
        } catch (Exception exception) {
            // call excption hangler here

            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));

        }

        return retURL;
    }

    // <editor-fold defaultstate="collapsed" desc="Delete Account">
    public String deleteAccount() {
        String retURL = "";

        // if account is selected and not chart root point
        if (this.selectedNode != null && !this.selectedNode.getData().toString().equals(chartOfAccount)) {

            ChartOfAccount chartAccount = (ChartOfAccount) this.selectedNode.getData();

            try {
                chartOfAccountDao.remove(chartAccount);

                JsfUtil.addMessageFromBundle("FinanceDAO_MESSAGE_DELETE_CHECK_SUCCESS", ConstantStrings.SEVERITY_INFO);

                retURL = "Reload";

            } catch (Exception exception) {

                JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));

            }

        }

        return retURL;

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Display Main Account">
    public void displayListner() {

        // if account is selected and not chart root point
        if (this.selectedNode != null && !this.selectedNode.getData().toString().equals(chartOfAccount)) {
            this.setSelectedAccount((ChartOfAccount) selectedNode.getData());
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="On Tab Change">
    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getId().equals("table_view")) {
            clearTreeSelection();
            renderTableView = Boolean.TRUE;
            selectedNode = null;
            selectedAccount = null;
        } else {
            clearTreeSelection();
            renderTableView = Boolean.FALSE;
            selectedNode = null;
            selectedAccount = null;
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Clear Tree Selection">
    private void clearTreeSelection() {
        List<TreeNode> expandedNodes = new ArrayList<>();
        expandedNodes.addAll(root.getChildren());

        List<TreeNode> tempNodes = new ArrayList<>();
        Iterator<TreeNode> nodesIter;
        TreeNode node;

        while (!expandedNodes.isEmpty()) {
            nodesIter = expandedNodes.iterator();
            while (nodesIter.hasNext()) {
                node = nodesIter.next();
                node.setSelected(Boolean.FALSE);
                node.setExpanded(Boolean.FALSE);
                tempNodes.addAll(node.getChildren());
            }
            expandedNodes.clear();
            expandedNodes.addAll(tempNodes);
            tempNodes.clear();
        }

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Retreive Account Normal Type">
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="View Main Accounts Report">
    public void viewMainAccountsReport() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("contentHeight", 600);
        options.put("height", 600);
        options.put("width", 950);
        options.put("modal", true);

        PrimeFaces.current().dialog().openDynamic("ViewMainAccountsReport", options, null);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setters&Getters">
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public ChartOfAccount getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(ChartOfAccount selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    public Boolean getRenderTableView() {
        return renderTableView;
    }

    public void setRenderTableView(Boolean renderTableView) {
        this.renderTableView = renderTableView;
    }

    public String getChartOfAccount() {
        return chartOfAccount;
    }

    public void setChartOfAccount(String chartOfAccount) {
        this.chartOfAccount = chartOfAccount;
    }

    //</editor-fold>
}
