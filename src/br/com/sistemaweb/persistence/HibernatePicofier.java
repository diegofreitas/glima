/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 12/02/2009 18:43:19
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.persistence;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.reflection.ReflectionUtil;
import org.hibernate.type.Type;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sistemaweb.IApplicationContext;
import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.utils.Utils;

public class HibernatePicofier extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ENTITY_ID_NAME = "id";

	private Logger log = LoggerFactory.getLogger(HibernatePicofier.class);

	//private final SessionFactory sessionFactory;
	private final MutablePicoContainer container;

	public HibernatePicofier(MutablePicoContainer container) {
		this.container = container;
		//this.sessionFactory = sessionFactory;
	}
	

	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#instantiate(java.lang.String, org.hibernate.EntityMode, java.io.Serializable)
	 */
	@Override
	public Object instantiate(String entityName, EntityMode entityMode,Serializable id) {
		try {
			Class<?> clazz = this.getClass().getClassLoader().loadClass(entityName);
			Object newEntity = new DefaultPicoContainer(container).addComponent(clazz).getComponent(clazz);
			PropertyUtils.setProperty(newEntity, getIdProperty(clazz), id);
			return newEntity;
		} catch (IllegalAccessException e) {
			log.error("Exception in instantiate,", e);
			return null;
		} catch (InvocationTargetException e) {
			log.error("Exception in instantiate", e);
			return null;
		} catch (NoSuchMethodException e) {
			log.error("Exception in instantiate", e);
			return null;
		} catch (ClassNotFoundException e) {
			log.error("Exception in instantiate", e);
			return null;
		}
	}

	
	
	private String getIdProperty(Class clazz) throws CallbackException {
		try {
			return ENTITY_ID_NAME;
		} catch (HibernateException e) {
			throw new CallbackException("Error getting identifier property for class " + clazz, e);
		}
	}
}
