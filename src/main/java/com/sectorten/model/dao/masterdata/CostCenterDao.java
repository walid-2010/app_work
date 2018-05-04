/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao.masterdata;

import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.entities.transactions.CostCenter;
import com.sectorten.model.entities.transactions.CostCenter_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author al-kamel
 */
@Stateless
public class CostCenterDao extends AbstractDao<CostCenter> {

    public CostCenterDao() {
        super(CostCenter.class);
    }

    public String calcCostCenterParentChildCode(CostCenter costCenter, CostCenter groupCostCenter) {
        try {

            // set child classification to parent classification
            if (groupCostCenter != null) {
                groupCostCenter = find(groupCostCenter.getId());

            }

            costCenter.setParentId(groupCostCenter);

            if (costCenter.getParentId() != null) {
                costCenter.setParentCode(costCenter.getParentId().getCode());
            } else {
                costCenter.setParentCode("");
            }

            // get all childs account number as string
            List<String> costCenterChildrenNums = findCostCenterChildrenCode(groupCostCenter);

            // split account number to get child account number as string
            for (int ii = 0; ii < costCenterChildrenNums.size(); ii++) {
                String costCenterCode = costCenterChildrenNums.get(ii);
                // split account to parent and child
                String[] splitCode = costCenterCode.split("^" + costCenter.getParentCode());
                for (String splitCode1 : splitCode) {
                    costCenterChildrenNums.set(ii, splitCode1);
                }
            }

            // initialize child account number to 0 as minimum account number
            costCenter.setChildCode("0");

            // find maximum number in list
            for (String costCenterChildNum : costCenterChildrenNums) {
                try {
                    if (Long.parseLong(costCenter.getChildCode()) < Long.parseLong(costCenterChildNum)) {
                        costCenter.setChildCode(costCenterChildNum);

                    }
                } catch (NumberFormatException e) {
                }
            }
            // add 1 to maximum account number
            costCenter.setChildCode(((Long) (Long.parseLong(costCenter.getChildCode()) + Long.parseLong("1"))).toString());
            return ((Long) (Long.parseLong(costCenter.getChildCode()) + Long.parseLong("1"))).toString();
        } catch (Exception exception) {
//            costCenter.setChildCode("1");
//
//            Logger.getLogger(CommonMasterDataCostCentersBean.class.getName()).log(Level.SEVERE, null, exception);
//            return "1";
            throw exception;
        }
    }

    public List<String> findCostCenterChildrenCode(CostCenter costCenter) {

        // criteria builder
        CriteriaBuilder criBuilder = getEntityManager().getCriteriaBuilder();
        // query builder
        CriteriaQuery<String> selectCostCenterChildren = criBuilder.createQuery(String.class);
        // root point as reference point
        Root<CostCenter> fnCostCenterRoot = selectCostCenterChildren.from(CostCenter.class);

        // Predicate list to add conditions
        List<Predicate> predicates = new ArrayList<>();

        if (costCenter != null) {
            predicates.add(criBuilder.equal(fnCostCenterRoot.get(CostCenter_.parentId), costCenter));
        } else {
            // parent id is null
            predicates.add(criBuilder.isNull(fnCostCenterRoot.get(CostCenter_.parentId)));
        }
        // criteria query
        selectCostCenterChildren.select(fnCostCenterRoot.get(CostCenter_.code))
                .where(criBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<String> findCostCenterChildren = getEntityManager().createQuery(selectCostCenterChildren);
        try {
            return findCostCenterChildren.getResultList();
        } catch (Exception exception) {
            throw exception;
        }
    }

    public List<CostCenter> findAllCostCentersOrdByCode() {

        // criteria builder
        CriteriaBuilder criBuilder = getEntityManager().getCriteriaBuilder();
        // query builder
        CriteriaQuery<CostCenter> selectAllOrdered = criBuilder.createQuery(CostCenter.class);
        // root point as reference point
        Root<CostCenter> rootEntery = selectAllOrdered.from(CostCenter.class);

        // criteria query
        selectAllOrdered.select(rootEntery)
                .orderBy(criBuilder.asc(rootEntery.get(CostCenter_.code)));

        TypedQuery<CostCenter> allFnAccOrdered = getEntityManager().createQuery(selectAllOrdered);
        try {
            return allFnAccOrdered.getResultList();
        } catch (Exception exception) {
            throw exception;
        }
    }

}
