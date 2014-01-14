package de.rwth.swc.jsf.common;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BeanBase {

	protected void addGeneralInfoMessage(String infoMessage) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								infoMessage, null));
	}

	protected void addGeneralErrorMessage(String errorMessage) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						null));
	}

	protected void addMessageForException(Exception e) {
		addGeneralErrorMessage(e.getMessage());
		Logger.getLogger(this.getClass().getName()).log(Level.WARNING,
				e.getMessage());
	}

	protected String getRequestParameter(String parameterName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(parameterName);
	}

	protected Object getRequestBean(String beanName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(beanName);
	}

	protected ResourceBundle getResourceBundle(String baseName) {
		return ResourceBundle.getBundle(baseName, FacesContext
				.getCurrentInstance().getViewRoot().getLocale());
	}
}
