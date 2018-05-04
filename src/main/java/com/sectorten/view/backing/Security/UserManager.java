/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.Security;

import com.sectorten.model.common.ConstantStrings;
import com.sectorten.model.dao.masterdata.UserDao;
import com.sectorten.model.entities.masterdata.Components;
import com.sectorten.model.entities.masterdata.Modules;
import com.sectorten.model.entities.masterdata.User;
import com.sectorten.model.entities.masterdata.UserComponents;
import com.sectorten.model.entities.masterdata.UserModules;
import com.sectorten.model.exception.BusinessLogicViolationException;
import com.sectorten.model.exception.EmptyResultSetException;
import com.sectorten.view.backing.common.ApplicationDataBacking;
import com.sectorten.view.common.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named("userManager")
public class UserManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);
    private ResourceBundle myBundle = ResourceBundle.getBundle("MyBundle", new Locale("en"));

    private List<String> userFormList;

    /**
     * Creates a new instance of UserManager
     */
    public UserManager() {
    }

    private User currentUser;
    //private Long userId;
    private String userName;
    private String password;
    private String originalURL;
    private MenuModel model;
//    private List<Form> userForms;
//    private SeGroup currentGroup;
    private List<UserModules> userModlues;
    private List<Modules> modules;
    private List<Components> components;
    private List<UserComponents> userComponentses;
    private String defaultLanguage;
//    private List<Parameters> paramList = new ArrayList();
    @Inject
    private UserDao userDao;
    @Inject
    private ApplicationDataBacking applicationDataBacking;

    @PostConstruct
    public void init() {
        myBundle = ResourceBundle.getBundle("MyBundle", Locale.forLanguageTag(ConstantStrings.AR));

    }

    public void preRenderView() {
        //Locale browserLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        myBundle = ResourceBundle.getBundle("MyBundle", Locale.forLanguageTag(ConstantStrings.AR));

        FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.forLanguageTag(ConstantStrings.AR));
    }

    public void login() {
        try {
            currentUser = userDao.authenticateUser(userName, password);
            if (currentUser != null) {
                if (originalURL == null) {
                    originalURL = "index.xhtml";
                }

                loadMenu();
                getExternalContext().redirect(originalURL);
            }
        } catch (EmptyResultSetException emptyResultSetException) {
            JsfUtil.showErrorMessage(myBundle.getString(emptyResultSetException.getMessage()));

        } catch (BusinessLogicViolationException businessLogicViolationException) {
            JsfUtil.showErrorMessage(myBundle.getString(businessLogicViolationException.getMessage()));

        } catch (Exception exception) {
            LOGGER.error("error_general", exception);

            JsfUtil.showErrorMessage(myBundle.getString("error_general"));

        }

    }

    public boolean authorizeURL(String url) {
        boolean valide = false;

        if (userModlues == null || userModlues.isEmpty()) {
            userModlues = currentUser.getUserModules();
        }

        return valide;

    }

    public String getSystemParam(String branchno, String parmName) {
        try {
//            Parameters param = new Parameters();
//            param = paramList.stream() // Convert to steam
//                    .filter(par -> (par.getParametersPK().getParam()).equals(parmName)) // filter on param name and branch
//                    .filter(par -> (par.getParametersPK().getBranchno()).equals(branchno))
//                    .findFirst()// If 'findAny' then return found
//                    .orElse(null);                                                       // If not found, return null
//            if (param == null)//get branch 0
//            {
//                param = paramList.stream()
//                        .filter(par -> (par.getParametersPK().getParam()).equals(parmName))
//                        .filter(par -> (par.getParametersPK().getBranchno()).equals("0"))
//                        .findFirst()
//                        .orElse(null);    // If not found, return null
//            }
//            if (param != null) {
//                return param.getValue();
//            } else {
//                return null;
//            }
            return null;
        } catch (Exception ex) {
            LOGGER.error("error_general", ex);
            return null;
        }

    }

    //load system parameters
    private void loadParam(String branchno) {
        Map param = new HashMap();
        param.put("branchNo", branchno);

        try {
            //paramList = paramtersDao.executeNamedQuery("Parameters.findByBranch", param);
        } catch (Exception ex) {
            LOGGER.error("error_general", ex);
        }
//        Set<Parameters> paramSet;
//        paramSet=new HashSet ( paramList );
//        paramList=new ArrayList();
//        paramList.add(paramSet);

    }

    /**
     * load current role screens
     */
    public void loadMenu1() {
        try {

            model = new DefaultMenuModel();

            model = new DefaultMenuModel();
            DefaultMenuItem item = null;
            DefaultSubMenu submenu = null;
            Set set = new HashSet();
            modules = new ArrayList();
            userComponentses = currentUser.getUserComponents();
            userModlues = currentUser.getUserModules();
            components = new ArrayList<>();

            for (UserModules userModules : userModlues) {

                modules.add(userModules.getModules());

            }

            for (UserComponents userComponents : userComponentses) {

                components.add(userComponents.getComponents());

            }

            for (Components compVar : components) {
                submenu = new DefaultSubMenu();
                submenu.setLabel(compVar.getDescription());
                for (Modules moduleVar : modules) {
                    if (moduleVar.getCompId().equals(compVar)) {

                        item = new DefaultMenuItem();
                        item.setValue(moduleVar.getDescription());
                        item.setUrl(moduleVar.getPath());
                        submenu.addElement(item);
                    }
                }
                if (submenu.getElementsCount() > 0) {
                    model.addElement(submenu);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("", ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, myBundle.getString(ex.getMessage()), "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public boolean canAccess() {
        return canAccess(null);
    }

    public boolean canAccess(String path) {
        boolean valid = false;
        int xhtmlIndex = path.lastIndexOf(".xhtml");

        String formName = path.substring(path.lastIndexOf("/"), xhtmlIndex).replaceAll("/", "");

        if (formName.equals("index") || formName.equals("Security") || formName.equals("login") || formName.equals("loginError")) {
            valid = true;
        } else {

            if (userFormList != null && !userFormList.isEmpty()) {

                if (userFormList.contains(formName)) {

                    valid = true;

                }
            }
        }
        return valid;
    }

    /*
     * the below method commented as Queey falg not used in live data and we will depend only on access flag
     */
//    public boolean canSearch() {
//        Form form = getCurrentForm(null);
//        if (form != null) {
//            if (form.getSeGroupFormsList().stream().filter((groupForm) -> (groupForm.getGroups().equals(currentGroup))).anyMatch((groupForm) -> (groupForm.getQueryFlag() == 0))) {
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean canEdit() {
//        Form form = getCurrentForm(null);
//        if (form != null) {
//            if (form.getSeGroupFormsList().stream().filter((groupForm) -> (groupForm.getGroups().equals(currentGroup))).anyMatch((groupForm) -> (groupForm.getUpdateFlag() == 0))) {
//                return true;
//            }
//        }
        return false;
    }

    public boolean canCreate() {
//        Form form = getCurrentForm(null);
//        if (form != null) {
//            if (form.getSeGroupFormsList().stream().filter((groupForm) -> (groupForm.getGroups().equals(currentGroup))).anyMatch((groupForm) -> (groupForm.getInsertFlag() == 0))) {
//                return true;
//            }
//        }
        return false;
    }

    public boolean canDelete() {
//        Form form = getCurrentForm(null);
//        if (form != null) {
//            if (form.getSeGroupFormsList().stream().filter((groupForm) -> (groupForm.getGroups().equals(currentGroup))).anyMatch((groupForm) -> (groupForm.getDeleteFlag() == 0))) {
//                return true;
//            }
//        }
        return false;
    }

    public boolean isFromAdmin() {
//        Form form = getCurrentForm(null);
//        if (form != null) {
//            if (form.getSeGroupFormsList().stream().filter((groupForm) -> (groupForm.getGroups().equals(currentGroup))).anyMatch((groupForm) -> (groupForm.getDeleteFlag() == 0))) {
//                return true;
//            }
//        }
        return false;
    }

//    private Form getCurrentForm(String path) {
//        if (path == null) {
//            path = getScreenPath();
//        }
//        for (Form form : userForms) {
//            if (form.getFormPath() != null && form.getFormPath().equals(path)) {
//                return form;
//            }
//        }
//        return null;
//    }
    public List<Components> findUserCompoenets(List<Components> componentList, Components components) {
        if (components != null && !componentList.contains(components)) {
            componentList.add(components);
            findUserCompoenets(componentList, components.getParentId());
        }
        return componentList;
    }

    public void loadMenu() {
        model = new DefaultMenuModel();

        try {

            modules = new ArrayList();
            userFormList = new ArrayList<>();
            // userComponentses = UserComponents.findAll();

            components = new ArrayList<>();
            if (currentUser.getIsAdmin()) {
                List<Modules> allAdminModules = applicationDataBacking.getModuleList();
                for (Modules adminModule : allAdminModules) {

                    if (!adminModule.isNonMenu()) {
                        modules.add(adminModule);
                    }
                    userFormList.add(adminModule.getFormName());
                    components = findUserCompoenets(components, adminModule.getCompId());

                }
            } else {
                userModlues = currentUser.getUserModules();
                for (UserModules userModules : userModlues) {

                    if (!userModules.getModules().isNonMenu()) {
                        modules.add(userModules.getModules());
                    }
                    userFormList.add(userModules.getModules().getFormName());
                    components = findUserCompoenets(components, userModules.getModules().getCompId());

                }
            }

//            for (UserComponents userComponents : userComponentses) {
//
//                components.add(userComponents.getComponents());
//
//            }
            // components = componentsDao.findAll();
            userModlues = currentUser.getUserModules();
            // copy components list to loop on it and add component parent to original list
            List<Components> componentsListCopy = new ArrayList<>(components);

            // loop to get component parents recursivly and add it to original list
            for (Integer index = 0; index < componentsListCopy.size(); index++) {
                // component parents list
                List<Components> compParentsList = new ArrayList<>();
                // currenet component in components list without parents
                Components currentComp = componentsListCopy.get(index);

                // while current component has parent
                while (!Objects.equals(currentComp.getParentId(), null)) {
                    currentComp = currentComp.getParentId();
                    // if component is not in original components list
                    if (!components.contains(currentComp)) {
                        // add parent to list
                        compParentsList.add(currentComp);
                    }
                }

                // add parents list to original components list
                components.addAll(compParentsList);
            }

            // order components list depending on level then ordering column
            // bubble sort using two keys first component level then ordering
            Components tempComponents;
            Boolean swapped = Boolean.TRUE;
            Integer jj = 0;
            while (swapped) {
                swapped = Boolean.FALSE;
                jj++;

//                for (Integer ii = 0; ii < components.size() - jj; ii++) {
//                    if (components.get(ii).getCompLevel() > components.get(ii + 1).getCompLevel()
//                            || (components.get(ii).getCompLevel() >= components.get(ii + 1).getCompLevel()
//                            && (components.get(ii).getOrdring() != null && components.get(ii + 1).getOrdring() != null) && componentsList.get(ii).getOrdring() > componentsList.get(ii + 1).getOrdring())) {
//                        // order using level then ordering field
//                        tempComponents = components.get(ii);
//                        components.set(ii, components.get(ii + 1));
//                        components.set(ii + 1, tempComponents);
//                        swapped = Boolean.TRUE;
//                    }
//                }
            }

            for (Components currComp : components)//adding components to sidemenu tree
            {

                DefaultSubMenu compSubMenu = new DefaultSubMenu(currComp.getDescription());
                compSubMenu.setId(currComp.getId().toString());

                List<MenuElement> compChildsList = model.getElements();
                Boolean compParentFound = Boolean.FALSE;

                while (!compParentFound && !compChildsList.isEmpty()) {

                    List<MenuElement> tempChildsList = new ArrayList<>();

                    for (MenuElement menuElement : compChildsList) {
                        if (menuElement instanceof org.primefaces.model.menu.DefaultSubMenu) {
                            tempChildsList.addAll(((DefaultSubMenu) menuElement).getElements());
                            if (currComp.getParentId() != null && menuElement.getId() != null && menuElement.getId().equals(currComp.getParentId().getId().toString())) {

                                ((DefaultSubMenu) menuElement).addElement(compSubMenu);

                                compParentFound = Boolean.TRUE;
                                break;
                            }
                        }
                    }
                    compChildsList = tempChildsList;
                }

                try {

                    Collection<Modules> mods = modules;
                    for (Modules currMod : mods) {

                        DefaultMenuItem item;

                        try {
                            item = new DefaultMenuItem(currMod.getDescription());
                            item.setUrl(currMod.getPath());
                            if (compSubMenu.getId().equals(currMod.getCompId().getId() + "")) {
//                                item.setIcon(currMod.getIconclass());
                                compSubMenu.addElement(item);
                            }
                        } catch (Exception e) {
                            LOGGER.error("error_general", e);
                        }

                    }

                    if (!compParentFound) {
                        model.addElement(compSubMenu);
                    }
                } catch (Exception e) {
                    LOGGER.error("error_general", e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("error_general", e);
        }
    }

    private String getScreenPath() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getServletPath();
    }

    public String logout() {
        getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";//
    }

    private ExternalContext getExternalContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return externalContext;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public List<String> getUserFormList() {
        return userFormList;
    }

    public void setUserFormList(List<String> userFormList) {
        this.userFormList = userFormList;
    }

}
