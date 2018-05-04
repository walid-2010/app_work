/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nbb
 */
public class BaseDao {

    @PersistenceContext(unitName = "WORK_APP_PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
}
