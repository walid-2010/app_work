/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.masterdata.costCenters;

import com.sectorten.business.services.transactions.IMasterDataServices;
import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.masterdata.CostCenterDao;
import com.sectorten.model.entities.transactions.CostCenter;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.backing.common.BaseBackBean;
import com.sectorten.view.common.JsfUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.util.ObjectUtils;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author waleed
 */
@Named(value = "costCentersTreeBB")
@ViewScoped
public class CostCentersTreeBB extends BaseBackBean implements Serializable {

    private CostCenter selectedCostCenter;

    private TreeNode root, selectedNode;
    private Boolean renderTableView = Boolean.TRUE;
    private BigDecimal localDebitSum = BigDecimal.ZERO,
            localCreditSum = BigDecimal.ZERO,
            localBalance = BigDecimal.ZERO;

    @Inject
    private CostCenterDao costCenterDao;
    @Inject
    private IMasterDataServices masterDataServices;

    /**
     * Creates a new instance of CostCentersTreeBB
     */
    public CostCentersTreeBB() {
    }

    // <editor-fold defaultstate="collapsed" desc="Initialization">
    @PostConstruct
    void init() {
        try {

            List<CostCenter> costCentersList = costCenterDao.findAllCostCentersOrdByCode();

            root = this.buildCostCentersTree(costCentersList);
        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Build Accounts Tree">
    private TreeNode buildCostCentersTree(List<CostCenter> costCenterList) {

        TreeNode rootNode, node;
        try {

            rootNode = new DefaultTreeNode("root", "costCenters", null);

            List<TreeNode> tempList;
            List<TreeNode> accountNodeChildren = rootNode.getChildren();
            Iterator<CostCenter> costCentersIter = costCenterList.iterator();

            //1-select nodes where parent id == null ,add to root ,remove from main list
            while (costCentersIter.hasNext()) {
                CostCenter costCenter = costCentersIter.next();
                if (ObjectUtils.equals(costCenter.getParentId(), null)) {
                    node = new DefaultTreeNode("child", costCenter, null);
                    accountNodeChildren.add(node);

                    costCentersIter.remove();
                }
            }

            //2-Find parent children
            while (!accountNodeChildren.isEmpty()) {
                tempList = new ArrayList<>();
                for (TreeNode parent : accountNodeChildren) {
                    Iterator<CostCenter> modifiedIter = costCenterList.iterator();
                    while (modifiedIter.hasNext()) {
                        CostCenter child = modifiedIter.next();
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
            Logger.getLogger(CostCentersTreeBB.class.getName()).log(Level.SEVERE, null, exception);
        }

        return null;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Display Cost Center">
    public void displayListner() {

        // if cost center is selected and not chart root point
        if (this.selectedNode != null && !this.selectedNode.getData().toString().equals("costCenters")) {
            this.setSelectedCostCenter((CostCenter) selectedNode.getData());
        }

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add Cost Center">
    public String addCostCenter() {

        String retAction = "";

        if (this.selectedNode != null && !this.selectedNode.getData().toString().equals("costCenters")) {
            this.setSelectedCostCenter((CostCenter) selectedNode.getData());

            this.putEditedObject(selectedCostCenter);
            this.putAddEditMode(ConstantStrings.ADD);

            retAction = "AddEdit";
        } else if (this.selectedNode == null
                || (this.selectedNode != null && this.selectedNode.getData().toString().equals("costCenters"))) {
            this.putEditedObject(null);
            this.putAddEditMode(ConstantStrings.ADD);
            retAction = "AddEdit";
        }

        return retAction;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Add Dimension Center">
    public String addDimensionCenter() {

        this.putEditedObject(null);
        this.putAddEditMode(ConstantStrings.ADD);

        return "AddEdit";
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Edit Cost Center">
    public String editCostCenter() {

        String retAction = "";

        if (this.selectedNode != null && !this.selectedNode.getData().toString().equals("costCenters")) {
            this.setSelectedCostCenter((CostCenter) selectedNode.getData());

            this.putEditedObject(selectedCostCenter);
            this.putAddEditMode(ConstantStrings.EDIT);

            retAction = "AddEdit";
        } else {
            JsfUtil.addMessageFromBundle("navigation_error", ConstantStrings.SEVERITY_ERROR);
        }

        return retAction;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Delete Cost Center">
    public String removeCostCenter() {
        String retURL = "";

        try {
            // if account is selected and not chart root point
            if (this.selectedNode != null && !this.selectedNode.getData().toString().equals("costCenters")) {

                CostCenter costCenter = (CostCenter) this.selectedNode.getData();

                masterDataServices.removeCostCenter(costCenter);

                JsfUtil.addMessageFromBundle("save_operation_success", ConstantStrings.SEVERITY_INFO);

                this.keepFlashMessage();

                retURL = "Reload";

            } else {
                JsfUtil.addMessageFromBundle("ERROR_DELTE_ITEM_LINE", ConstantStrings.SEVERITY_ERROR);
            }
        } catch (Exception exception) {
            JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(exception));

        }
        return retURL;

    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="On Tab Change">
    public void onTabChange(TabChangeEvent event) {
        clearTreeSelection();
        selectedNode = null;
        selectedCostCenter = null;

        if (event.getTab().getId().equals("table_view")) {
            renderTableView = Boolean.TRUE;
        } else {
            renderTableView = Boolean.FALSE;
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

    // <editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public CostCenter getSelectedCostCenter() {
        return selectedCostCenter;
    }

    public void setSelectedCostCenter(CostCenter selectedCostCenter) {
        this.selectedCostCenter = selectedCostCenter;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Boolean getRenderTableView() {
        return renderTableView;
    }

    public void setRenderTableView(Boolean renderTableView) {
        this.renderTableView = renderTableView;
    }

    public BigDecimal getLocalDebitSum() {
        return localDebitSum;
    }

    public void setLocalDebitSum(BigDecimal localDebitSum) {
        this.localDebitSum = localDebitSum;
    }

    public BigDecimal getLocalCreditSum() {
        return localCreditSum;
    }

    public void setLocalCreditSum(BigDecimal localCreditSum) {
        this.localCreditSum = localCreditSum;
    }

    public BigDecimal getLocalBalance() {
        return localBalance;
    }

    public void setLocalBalance(BigDecimal localBalance) {
        this.localBalance = localBalance;
    }

    // </editor-fold>
}
