package br.com.sistemaweb.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.query.CriteriaExampleQueryObject;
import br.com.sistemaweb.persistence.query.CriteriaQueryObject;
import br.com.sistemaweb.persistence.query.IQueryObject;
import br.com.sistemaweb.utils.Utils;




public class GenericRepository<T  extends IDomainObject> implements IRepository<T>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class<T> domainClass;
	
	private IQueryObject<T> defaultQueryObject;

	protected Logger log =LoggerFactory.getLogger(GenericRepository.class);
	
	public GenericRepository() {
		
	}
	
	public GenericRepository(Class<T> domainClass) {
		this.domainClass=domainClass;
		defaultQueryObject=new CriteriaExampleQueryObject<T>(domainClass);
	}

	@Override
	public Class<T> getDomainClass() {
		return domainClass;
	}

	@Override
	public final void setDomainClass(Class<T> clazz) {
		 domainClass=clazz;
	}

	
	@Override
	public final T get(Long id) {
		T obj= (T) getSession().load(domainClass, id);
		return obj;
	}

	@Override
	public final void remove(T t) throws Exception{
		getSession().delete(t);
		getSession().flush();
	}

	@Override
	public Collection<T> toCollection() {
		return (List<T>) getDefaultQueryObject().list();
	}

	@Override
	public T create() {
		T obj=null;
		try {
			obj=domainClass.newInstance();
			obj.setRepository(this);
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<T> toCollection(Object obj) {
		getDefaultQueryObject().setSpecification((T)obj);
		return getDefaultQueryObject().list();
	}
	
	@Override
	public Session getSession() {
		return Utils.getApplicationContext().getHibernateSession();
	}

	@Override
	public void persist(T t) {
		try{
			if(t.getVersion()==null){
				getSession().save(t);
			}else{
				getSession().update(t);
			}
		} catch (NonUniqueObjectException e) {
			getSession().merge(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IQueryObject<T> getDefaultQueryObject() {
		return defaultQueryObject;
	}

	public void setDefaultQueryObject(IQueryObject<T> defaultQueryObject) {
		this.defaultQueryObject = defaultQueryObject;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.persistence.IRepository#refresh(java.lang.Object)
	 */
	@Override
	public void refresh(T t) {
		getSession().refresh(t);
	}

}
