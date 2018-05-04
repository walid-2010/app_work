/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sectorten.view.backing.common;

import com.sectorten.model.entities.masterdata.User;
import com.sectorten.model.exception.BusinessLogicViolationException;
import com.sectorten.view.backing.Security.UserManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author
 */
@Named(value = "baseBackBean")
@ViewScoped
public class BaseBackBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBackBean.class);
    protected String absoluteFilePath;
    //navigation with object instead of id then find
    protected static final String EDITED_OBJECT_KEY = "editedObject";
    protected static final String ADD_EDIT_MODE = "addEditMode";
    protected static final String MSG_KEY = "msg";
    private static final String USER_KEY = "userID";
    private String reports_domain = "http://";
    protected List<Boolean> toggleBooleanList;
    protected Boolean advancedSearch;
    @Inject
    protected UserManager userManager;

    private ResourceBundle myBundle = ResourceBundle.getBundle("MyBundle", new Locale("en"));

    public BaseBackBean() {

    }

    public void preRenderView() {
        userManager.preRenderView();
    }

    @PostConstruct
    void init() {

    }

    //todo put user object in session when login
    public User getUser() {
        //return (User) this.getSessionMap().get(USER_KEY);
        return userManager.getCurrentUser();
    }

    public String getReports_domain() {
        return reports_domain;
    }

    public void keepFlashMessage() {
        FacesContext.getCurrentInstance()
                .getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
    }

    public void setReports_domain(String reports_domain) {
        this.reports_domain = reports_domain;
    }

    //start navigation with object instead of id then find
    private Map<String, Object> getSessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap();
    }

    public void putInSessionMap(String key, Object value) {
        this.getSessionMap().put(key, value);
    }

    public Object getEditedObject() {
        return this.getSessionMap().get(EDITED_OBJECT_KEY);
    }

    public void putEditedObject(Object editedObject) {
        this.putInSessionMap(EDITED_OBJECT_KEY, editedObject);
    }

    public Object getAddEditMode() {
        return this.getSessionMap().get(ADD_EDIT_MODE);
    }

    public void putAddEditMode(Integer addEditMode) {
        this.putInSessionMap(ADD_EDIT_MODE, addEditMode);
    }

    public void removeFromSessionMap(String key) {
        this.getSessionMap().remove(key);
    }

    //put msg in session like add done sucessfully after close add screaan and return to search page
    public String getMsg() {
        return (String) this.getSessionMap().get(MSG_KEY);
    }

    public void putMsg(String msg) {
        this.putInSessionMap(MSG_KEY, msg);
    }

    //end navigation with object instead of id then find
    //navigate
    public void navigate(String navOutcome) {
        FacesContext.getCurrentInstance().responseComplete();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null, navOutcome);
    }

    //Clear grid filter by Abd-Elwadod, Mohamed
    public void resetAllDataTableFilter(String datatableId) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(datatableId);
        if (dataTable != null && !dataTable.getFilters().isEmpty()) {
            dataTable.reset();
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update(datatableId);
        }
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    protected HttpSession getSession() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
    }

    protected Application getApplication() {
        return FacesContext.getCurrentInstance().getApplication();
    }

    protected boolean isEnglish() {
        return getParameterizedMessage("dir").equals("ltr");
    }

    @SuppressWarnings("rawtypes")
    protected String getCompleteURL(HttpServletRequest req) {
        String url = req.getRequestURI();
        Enumeration paramNames = req.getParameterNames();
        if (paramNames != null && paramNames.hasMoreElements()) {
            url += "?";
            String paramSeparator = "";
            do {
                String paramName = (String) paramNames.nextElement();
                url += paramSeparator + paramName + "=" + req.getParameter(paramName);
                paramSeparator = "&";
            } while (paramNames.hasMoreElements());
        }
        return url;
    }

    public void setFacesSuccessMessages(String message) {
        setFacesMessage(FacesMessage.SEVERITY_INFO, "", message);
    }

    public void setFacesErrorMessages(String message) {
        setFacesMessage(FacesMessage.SEVERITY_ERROR, "", message);
    }

    private void setFacesMessage(FacesMessage.Severity severity, String messageHeader, String messageDetail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, messageHeader, messageDetail));
    }

    public String getParameterizedMessage(String key, Object... params) {
        String value = myBundle.getString(key);
        return params == null ? value : MessageFormat.format(value, params);
    }

    public void changeLocale(String lang) {
        Locale locale = new Locale(lang);
        this.myBundle = ResourceBundle.getBundle("MyBundle", locale);
    }

    public void goToAccessDenied() {
        try {

            FacesContext.getCurrentInstance().getExternalContext().redirect(getRequest().getContextPath() + "/errorPages/accessDenied.xhtml");
        } catch (IOException ex) {
            setFacesErrorMessages(ex.getMessage());
        }
    }

    public void redirect(String path) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(getRequest().getContextPath() + path);
        } catch (IOException ex) {
            setFacesErrorMessages(ex.getMessage());
        }
    }

    public StreamedContent getTemplateXlsxFile(InputStream inputStream, String templateFileName) {
        StreamedContent streamedFile = null;
        try {
            streamedFile = new DefaultStreamedContent(inputStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", (templateFileName + ".xlsx"));
        } catch (Exception e) {
            LOGGER.error("Exception", e);
            setFacesErrorMessages(getParameterizedMessage("error_general"));
        }
        return streamedFile;
    }

    public String upload(String Path, UploadedFile uploadedFile, String fileName, Boolean showAlert) throws BusinessLogicViolationException {
        LOGGER.info("============================ Uploading ============================");
        try {
            String tempPath = null;
            String filename = Path + fileName;
            File file = new File(filename);
            file.setWritable(true, false);
            file.setReadable(true, false);
            file.setExecutable(true, false);
            if (file == null) {
                LOGGER.debug("file is null!!!!!!!!!!!!!!!!!!");
                throw new BusinessLogicViolationException("error_uploadFail");
            }
            file.getParentFile().mkdirs();
            LOGGER.debug(file.getAbsolutePath());
            LOGGER.debug(file.isFile() + "");
            FileOutputStream os = new FileOutputStream(file);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            int read;
            InputStream is = uploadedFile.getInputstream();
            file.createNewFile();
            while ((read = is.read(temp)) >= 0) {
                buffer.write(temp, 0, read);
            }
            buffer.writeTo(os);
            os.flush();
            os.close();
            absoluteFilePath = filename;
            if (showAlert) {
                setFacesSuccessMessages(getParameterizedMessage("notify_uplodingSuccessfully", new Object[]{uploadedFile.getFileName()}));
            }
            LOGGER.info("============================ End Uploading ============================");
            tempPath = absoluteFilePath;
            return tempPath;
        } catch (BusinessLogicViolationException ex) {
            throw ex;
        } catch (Exception e) {
            LOGGER.error("error_general", e);
            throw new BusinessLogicViolationException("error_general");
        }
    }

    /**
     * get original file name from file path
     *
     * @param filePath
     * @return
     */
    public String getFileName(String filePath) throws UnsupportedEncodingException {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("-")) + filePath.substring(filePath.lastIndexOf("."));
        return new String(fileName.getBytes("iso-8859-1"), "UTF-8");
    }

    /**
     * this function to parse the return message form the Exec funtion which
     * returned from the database functions
     *
     * @param returnErrorStr the String of the Error which returned From the
     * database function
     * @return List of ErrorDTO
     * @see ErrorDto
     *
     */
    public List parseError(String returnErrorStr) {
        List errorMsgsList = new ArrayList();

        return errorMsgsList;
    }

    public void showDBMsg(String returnErrorStr) {
        List errorMessagesList = parseError(returnErrorStr);
        String msg = "";
        String allMsg = "";
        Iterator msgIter = errorMessagesList.iterator();
        Severity msgSeverty = FacesMessage.SEVERITY_WARN;
    }

    public ResourceBundle getMyBundle() {
        return myBundle;
    }

    public void setMyBundle(ResourceBundle myBundle) {
        this.myBundle = myBundle;
    }

    public boolean checkExistItemList() {
        boolean exist = false;
        if (getSessionMap().containsKey("itemList")) {
            exist = true;

        }
        return exist;

    }

//    public List<Item> findExistItemList() {
//        List<Item> itemList = null;
//        if (checkExistItemList()) {
//            itemList = (List<Item>) getSessionMap().get("itemList");
//        }
//        return itemList;
//    }
//
//    public List<Item> getItemsList() {
//        List<Item> itemList = findExistItemList();
//        if (itemList == null) {
//            itemList = itemDao.findAll();
//        }
//        return itemList;
//    }
//
//    public List<StkAccounts> getCreditAccounts() {
//        return stockManagment.getStkAccountsByGroup(getUserBranch(), null);
//    }
    public List<Boolean> getToggleBooleanList() {
        return toggleBooleanList;
    }

    public void setToggleBooleanList(List<Boolean> toggleBooleanList) {
        this.toggleBooleanList = toggleBooleanList;
    }

    public void prePareToggleTable(DataTable dataTable) {
        if (dataTable.getColumns() != null && !dataTable.getColumns().isEmpty()) {
            int columnCount = dataTable.getColumns().size();
            Boolean[] booleanArray = new Boolean[columnCount];
            Arrays.fill(booleanArray, Boolean.TRUE);
            toggleBooleanList = Arrays.asList(booleanArray);

        }
    }

    public void onToggle(ToggleEvent e) {
        toggleBooleanList.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
    }

    public boolean canEdit() {
        return userManager.canEdit();
    }

    private String getReportServeletPath() {
        String servletPath = userManager.getSystemParam("0", "UReportServletPath");
        return servletPath;
    }

    private List<NameValuePair> prepareReportParams(String reportId, Map<String, String> params) {
        List<NameValuePair> data = new ArrayList();
        //fixed param

        String applicationIdKey = userManager.getSystemParam("0", "UReportServletApplicationNameKey");
        String applicationIdValue = userManager.getSystemParam("0", "UReportServletApplicationNameValue");
        String ReportIdKey = userManager.getSystemParam("0", "UReportServletReportIdKey");
        String BranchNoKey = userManager.getSystemParam("0", "UReportServletBranchNoKey");

        String DateKey = userManager.getSystemParam("0", "UReportServletDateKey");
        Long currentDateLong = (new Date()).getTime();

        data.add(new BasicNameValuePair(ReportIdKey, reportId));
        data.add(new BasicNameValuePair(applicationIdKey, applicationIdValue));

        data.add(new BasicNameValuePair(DateKey, currentDateLong.toString()));

        //variable param
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return data;
    }

    //<editor-fold defaultstate="collapsed" desc="This method for Data Tables which use filter events">
    public void filterListener(FilterEvent filterEvent) {
        /// System.err.println("Calling Filter");
    }

    //<editor-fold defaultstate="collapsed" desc="This method for Data Tables which use filter events">
    public void handleAdvancedSearchToggle(ToggleEvent event) {

        if (event.getVisibility().equals(Visibility.VISIBLE)) {
            advancedSearch = Boolean.TRUE;
        } else {
            advancedSearch = Boolean.FALSE;
        }
    }

    public Boolean getAdvancedSearch() {
        return advancedSearch;
    }

    public void setAdvancedSearch(Boolean advancedSearch) {
        this.advancedSearch = advancedSearch;
    }

}
