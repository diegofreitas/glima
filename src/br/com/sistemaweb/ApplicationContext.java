package br.com.sistemaweb;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import br.com.sistemaweb.persistence.AbstractHibernateSessionFilter;
import br.com.sistemaweb.security.IUser;

public class ApplicationContext implements IApplicationContext{

	
	
	public  Session getHibernateSession() {
		/* */
		HttpServletRequest request = getRequest();
		return (Session) request.getAttribute(AbstractHibernateSessionFilter.SESSION);
		//Para Teste
		/* *
		 return HibernateUtil.getCurrentSession();
		/* */
	}
	/**
	 * @return
	 */
	public  HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) getFacesContext()
				.getExternalContext().getRequest();
		return request;
	}

	public  FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}


	public  Object getSessionValue(String key) {
		return getFacesContext().getExternalContext().getSessionMap().get(key);
	}
	
	
	
	public  void setSessionValue(String key,Object obj) {
		getFacesContext().getExternalContext().getSessionMap().put(key,obj);
	}
	
	
	public  IUser getUser() {
		return (IUser) this.getSessionValue(IUser.USER);
	}
	
	

	
	
		
	
}
