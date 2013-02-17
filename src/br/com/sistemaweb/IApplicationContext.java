/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 11/02/2009 20:43:57
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;



/**
 * @author Diego Lins de Freitas
 *
 */
public interface IApplicationContext {

		public  Session getHibernateSession() ;

		public  HttpServletRequest getRequest() ;

		public  FacesContext getFacesContext() ;
		
		public Object getSessionValue(String key) ;

		public void setSessionValue(String key,Object obj) ;	

}


