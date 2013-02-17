package br.com.sistemaweb;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageFactory{
	
	
	public  void addErrorMessage(String id) {								//Utilizado nivel warn porque o error é para validação
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, getBundleMessage(id), ""));
	}

	public  void addInfoMessage(String id) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, getBundleMessage(id),""));
	}

	/**
	 * @param id
	 */
	public  void addMessage(String idComponet,String idResource) {
		FacesContext.getCurrentInstance().addMessage(idComponet, getResourceBundleMessage(idResource));
	}

	public  FacesMessage getResourceBundleMessage(String id) {
		String message="";
		try {
			message = ResourceBundle.getBundle("message").getString(id);
		} catch (MissingResourceException e) {
			return new FacesMessage(id);
		}
		return new FacesMessage(message);
	}
	
	public  String getBundleMessage(String messageId) {
		try {
			messageId = ResourceBundle.getBundle("message").getString(messageId);
		} catch (MissingResourceException e) {}
		return messageId;
	}
	
	
}