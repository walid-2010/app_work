package com.sectorten.view.common;

import com.sectorten.model.common.ConstantStrings;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

public class JsfUtil {

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addFatalMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static Locale getCurrentLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public static String getCurrentLanguage() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
    }

    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }

    public static String getMessageFromBunndle(String key) {
        try {
            Locale browserLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle myBundle = ResourceBundle.getBundle("MyBundle", browserLocale);
            return myBundle.getString(key);
        } catch (Exception exception) {
            return key;
        }
    }

    public static void addGlobalMessage(FacesMessage.Severity s, String header, String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s, header, details));
    }

    public static String getResourceBundleParameterizedValue(ResourceBundle bundle, String msgKey, Object[] paramValues) {

        if (getMessageFromBunndle(msgKey) == null) {
            return null;
        }

        String msgValue = bundle.getString(msgKey);
        if (msgValue != null && paramValues != null) {
            MessageFormat messageFormat = new MessageFormat(msgValue);
            return messageFormat.format(paramValues);
        }
        return msgValue;
    }

    public static String getResourceBundleParameterizedValue(String msgKey, Object[] paramValues) {
        Locale browserLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("MyBundle", browserLocale);
        return getResourceBundleParameterizedValue(myBundle, msgKey, paramValues);
    }

    public static void addMessageFromBundle(String messageKey, Integer messageType) {
        String message = getMessageFromBunndle(messageKey);
        if (Objects.equals(messageType, ConstantStrings.SEVERITY_ERROR)) {
            addErrorMessage(message);

        } else if (Objects.equals(messageType, ConstantStrings.SEVERITY_FATAL)) {
            addFatalMessage(message);

        } else if (Objects.equals(messageType, ConstantStrings.SEVERITY_WARN)) {
            addWarnMessage(message);

        } else if (Objects.equals(messageType, ConstantStrings.SEVERITY_INFO)) {
            addSuccessMessage(message);

        }

    }

    public static void showErrorMessage(String messageKey) {

        addMessageFromBundle(messageKey, ConstantStrings.SEVERITY_ERROR);
    }

    public static void showSuccessMessage(String returnedMessage) {
        if (returnedMessage != null && !returnedMessage.equals("")) {
            JsfUtil.addMessageFromBundle("success", ConstantStrings.SEVERITY_INFO);
        }
    }

    public static void showBusinessMessage(String returnedMessage) {
        if (returnedMessage != null && !returnedMessage.equals("")) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, returnedMessage, returnedMessage);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } else {
            JsfUtil.addMessageFromBundle("success", ConstantStrings.SEVERITY_INFO);
        }
    }

    public void updateComponentById(String clientIds) {
        PrimeFaces.current().ajax().update(clientIds);
    }

    public void updateListComponents(List<String> clientIds) {
        PrimeFaces.current().ajax().update(clientIds);
    }
}
