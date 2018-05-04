package com.sectorten.view.backing.common;

import com.sectorten.model.dao.AbstractDao;
import com.sectorten.model.exception.BusinessLogicViolationException;
import com.sectorten.model.exception.ExceptionHandler;
import com.sectorten.view.common.JsfUtil;
import com.sectorten.view.common.lazyModel.LazyEntityDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractBacking<T> extends BaseBackBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AbstractDao<T> ejbDao;

    private Class<T> itemClass;

    private T selected;

    private Collection<T> items;

    protected LazyEntityDataModel<T> lazyItems;

    private List<T> filteredItems;

    private boolean createMode;

    private boolean canAdd;
    private boolean canEdit;
    private boolean canDelete;
    private boolean canPost;

    private Integer moduleId;

    private enum PersistAction {

        CREATE,
        DELETE,
        UPDATE

    }

    public AbstractBacking() {
    }

    public AbstractBacking(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Retrieve the current EJB Dao object so that other beans in this package
     * can perform additional data layer tasks (e.g. additional queries)
     *
     * @return the concrete EJB Dao associated with the concrete controller
     * bean.
     */
    protected AbstractDao<T> getDao() {
        return ejbDao;
    }

    /**
     * Set any isChildEntityEmpty flags, if any children are defined in entity.
     * This method should be overridden inside specific entity controllers if
     * the entity has any OneToMany relationships. (see specific controllers for
     * more detail.
     *
     */
    protected void setChildrenEmptyFlags() {
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        if (selected != null) {
            if (this.selected == null || !this.selected.equals(selected)) {
                this.selected = this.ejbDao.findWithParents(selected);
                this.setChildrenEmptyFlags();
            }
        } else {
            this.selected = null;
        }
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public Collection<T> getItems() {
        if (items == null) {
            items = this.ejbDao.findAll();
        }
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }

    /**
     *
     * @return Entity-specific Lazy Data Model
     */
    public LazyEntityDataModel<T> getLazyItems() {
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel<>(this.ejbDao);
        }
        return lazyItems;
    }

    public void setLazyItems(LazyEntityDataModel<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    public void setLazyItems(Collection<T> items) {
        if (items instanceof List) {
            lazyItems = new LazyEntityDataModel<>((List<T>) items);
        } else {
            lazyItems = new LazyEntityDataModel<>(new ArrayList<>(items));
        }
    }

    public List<T> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<T> filteredItems) {
        this.filteredItems = filteredItems;
    }

    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = JsfUtil.getMessageFromBunndle("success_msg");
        if (createMode) {
            // msg = JsfUtil.getMessageFromBunndle(U.getSimpleName() + "Created");
            persist(PersistAction.CREATE, msg);
        } else {

            // msg = JsfUtil.getMessageFromBunndle(itemClass.getSimpleName() + "Updated");
            persist(PersistAction.UPDATE, msg);
        }

        if (!isValidationFailed()) {
//            if (this.items != null) {
//                // Update the existing entity inside the item list
//                List<T> itemList = refreshItem(this.selected, this.items);
//                // If the original list has changed (it is a new object)
//                if (this.items != itemList) {
//                    this.setItems(itemList);
//                }
//                // Also refresh the filteredItems list in case the user has filtered the DataTable
//                if (filteredItems != null) {
//                    refreshItem(this.selected, this.filteredItems);
//                }
//            }
            initParams();

        }

    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        String msg = JsfUtil.getMessageFromBunndle("success_msg");
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
            lazyItems = null; // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {

        String msg = JsfUtil.getMessageFromBunndle("success_msg");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
            lazyItems = null; // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    this.ejbDao.edit(selected);
                } else {
                    this.ejbDao.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (BusinessLogicViolationException businessException) {
                JsfUtil.addErrorMessage(ExceptionHandler.exceptionHandler(businessException));
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        createMode = true;
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        advancedSearch = false;
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            setItems((Collection<T>) paramItems);
            setLazyItems((Collection<T>) paramItems);
        }
    }

    private List<T> refreshItem(T item, Collection<T> items) {
        // Use List#set to replace the existing instance of this entity
        // If items is not a List, convert the Collection to a List
        List<T> itemList;
        if (this.items instanceof List) {
            itemList = (List<T>) items;
        } else {
            itemList = new ArrayList<>(items);
        }
        int i = itemList.indexOf(item);
        if (i >= 0) {
            try {
                itemList.set(i, item);
            } catch (UnsupportedOperationException ex) {
                return refreshItem(item, new ArrayList<>(items));
            }
        }
        return itemList;
    }

    public void prepareEdit(ActionEvent event) {
        createMode = false;
    }

    public boolean isCreateMode() {
        return createMode;
    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
    }

    public void setSelectedAfterChange(T selected) {
        if (selected != null) {
            this.selected = selected;
        } else {
            this.selected = null;
        }
    }

    protected Integer getModuleId() {
        return moduleId;
    }

    protected void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    protected boolean isCanAdd() {
        return canAdd;
    }

    protected void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    protected boolean isCanEdit() {
        return canEdit;
    }

    protected void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    protected boolean isCanDelete() {
        return canDelete;
    }

    protected void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    protected boolean isCanPost() {
        return canPost;
    }

    protected void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

}
