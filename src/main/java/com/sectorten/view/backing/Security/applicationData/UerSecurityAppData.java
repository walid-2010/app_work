/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.Security.applicationData;

import com.sectorten.model.dao.masterdata.ComponentsDao;
import com.sectorten.model.entities.masterdata.ModulesOperations;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author nbb
 */
@Named
@ApplicationScoped
public class UerSecurityAppData implements Serializable {

    @Inject
    private ComponentsDao componentsDao;

    private List<ModulesOperations> modulesOperationList;

    @PostConstruct
    public void init() {

    }

}
