/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao.masterdata;

import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.entities.masterdata.Components;
import com.sectorten.model.entities.masterdata.Components_;
import com.sectorten.model.entities.masterdata.Modules;
import com.sectorten.model.entities.masterdata.Modules_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author nbb
 */
@Stateless
public class ComponentsDao extends AbstractDao<Components> {

    public ComponentsDao() {
        super(Components.class);
    }

    public List<Components> findMoulesComponents() {

        CriteriaBuilder criBuilder = getEntityManager().getCriteriaBuilder();
        // query builder
        CriteriaQuery<Components> critQuery = criBuilder.createQuery(Components.class);
        // root point as reference point
        Root<Components> compoentRoot = critQuery.from(Components.class);
        Join<Components, Modules> moduelJoin = compoentRoot.join(Components_.modulesList);//

        List<Predicate> predicates = new ArrayList<>();

        // where company_id is user company
        predicates.add(criBuilder.isNull(moduelJoin.get(Modules_.compId).get(Components_.parentId)));

        critQuery.select(compoentRoot)
                .where(criBuilder.and(predicates.toArray(new Predicate[predicates.size()]))).distinct(true);

        TypedQuery<Components> getCompanyBanks = getEntityManager().createQuery(critQuery);

        return getCompanyBanks.getResultList();

    }

}
