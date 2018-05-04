/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao.transactions;

import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.entities.transactions.ChartOfAccount;
import com.sectorten.model.entities.transactions.ChartOfAccount_;
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
public class ChartOfAccountDao extends AbstractDao<ChartOfAccount> {

    public ChartOfAccountDao() {
        super(ChartOfAccount.class);
    }

    public List<ChartOfAccount> findAllOrderedById() throws Exception {
        try {
            // criteria builder
            CriteriaBuilder criBuilder = getEntityManager().getCriteriaBuilder();
            // query builder
            CriteriaQuery<ChartOfAccount> selectAllOrdered = criBuilder.createQuery(ChartOfAccount.class);
            // root point as reference point
            Root<ChartOfAccount> rootEntery = selectAllOrdered.from(ChartOfAccount.class);

            // criteria query
            selectAllOrdered.select(rootEntery)
                    .orderBy(criBuilder.asc(rootEntery.get(ChartOfAccount_.accountNo)));

            TypedQuery<ChartOfAccount> allChartOfAccountOrdered = getEntityManager().createQuery(selectAllOrdered);

            return allChartOfAccountOrdered.getResultList();
        } catch (Exception exception) {
            throw exception;
        }
    }

    public String calcChartOfAccountParentChildCode(ChartOfAccount ChartOfAccount, ChartOfAccount groupChartOfAccount) {
        try {

            // set child classification to parent classification
            if (groupChartOfAccount != null) {
                groupChartOfAccount = find(groupChartOfAccount.getAccountID());

            }

            ChartOfAccount.setParentId(groupChartOfAccount);

            if (ChartOfAccount.getParentId() != null) {
                ChartOfAccount.setParentCode(ChartOfAccount.getParentId().getAccountNo());
            } else {
                ChartOfAccount.setParentCode("");
            }

            // get all childs account number as string
            List<String> ChartOfAccountChildrenNums = findChartOfAccountChildrenCode(groupChartOfAccount);

            // split account number to get child account number as string
            for (int ii = 0; ii < ChartOfAccountChildrenNums.size(); ii++) {
                String ChartOfAccountCode = ChartOfAccountChildrenNums.get(ii);
                // split account to parent and child
                String[] splitCode = ChartOfAccountCode.split("^" + ChartOfAccount.getParentCode());
                for (String splitCode1 : splitCode) {
                    ChartOfAccountChildrenNums.set(ii, splitCode1);
                }
            }

            // initialize child account number to 0 as minimum account number
            ChartOfAccount.setChildCode("0");

            // find maximum number in list
            for (String ChartOfAccountChildNum : ChartOfAccountChildrenNums) {
                try {
                    if (Long.parseLong(ChartOfAccount.getChildCode()) < Long.parseLong(ChartOfAccountChildNum)) {
                        ChartOfAccount.setChildCode(ChartOfAccountChildNum);

                    }
                } catch (NumberFormatException e) {
                }
            }
            // add 1 to maximum account number
            ChartOfAccount.setChildCode(((Long) (Long.parseLong(ChartOfAccount.getChildCode()) + Long.parseLong("1"))).toString());
            return ((Long) (Long.parseLong(ChartOfAccount.getChildCode()) + Long.parseLong("1"))).toString();
        } catch (Exception exception) {
//            ChartOfAccount.setChildCode("1");
//
//            Logger.getLogger(CommonMasterDataChartOfAccountsBean.class.getName()).log(Level.SEVERE, null, exception);
//            return "1";
            throw exception;
        }
    }

    public List<String> findChartOfAccountChildrenCode(ChartOfAccount ChartOfAccount) {

        // criteria builder
        CriteriaBuilder criBuilder = getEntityManager().getCriteriaBuilder();
        // query builder
        CriteriaQuery<String> selectChartOfAccountChildren = criBuilder.createQuery(String.class);
        // root point as reference point
        Root<ChartOfAccount> fnChartOfAccountRoot = selectChartOfAccountChildren.from(ChartOfAccount.class);

        // Predicate list to add conditions
        List<Predicate> predicates = new ArrayList<>();

        if (ChartOfAccount != null) {
            predicates.add(criBuilder.equal(fnChartOfAccountRoot.get(ChartOfAccount_.parentId), ChartOfAccount));
        } else {
            // parent id is null
            predicates.add(criBuilder.isNull(fnChartOfAccountRoot.get(ChartOfAccount_.parentId)));
        }
        // criteria query
        selectChartOfAccountChildren.select(fnChartOfAccountRoot.get(ChartOfAccount_.accountNo))
                .where(criBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<String> findChartOfAccountChildren = getEntityManager().createQuery(selectChartOfAccountChildren);
        try {
            return findChartOfAccountChildren.getResultList();
        } catch (Exception exception) {
            throw exception;
        }
    }

    public List<ChartOfAccount> findAllChartOfAccountsOrdByCode() {

        // criteria builder
        CriteriaBuilder criBuilder = getEntityManager().getCriteriaBuilder();
        // query builder
        CriteriaQuery<ChartOfAccount> selectAllOrdered = criBuilder.createQuery(ChartOfAccount.class);
        // root point as reference point
        Root<ChartOfAccount> rootEntery = selectAllOrdered.from(ChartOfAccount.class);

        // criteria query
        selectAllOrdered.select(rootEntery)
                .orderBy(criBuilder.asc(rootEntery.get(ChartOfAccount_.accountNo)));

        TypedQuery<ChartOfAccount> allFnAccOrdered = getEntityManager().createQuery(selectAllOrdered);
        try {
            return allFnAccOrdered.getResultList();
        } catch (Exception exception) {
            throw exception;
        }
    }

}
