package br.com.sistemaweb.persistence;

import java.util.Collection;

import org.hibernate.Session;

import br.com.sistemaweb.persistence.query.IQueryObject;

public interface IRepository<T> {

	public void setDomainClass(Class<T> clazz);
	
	public Class<T> getDomainClass();
	
	public T get(Long id);
	
	public void remove(T t) throws Exception;
	
	public Collection<T> toCollection();
	
	public Collection<T> toCollection(Object obj);
	
	public T create();

	public Session getSession();
	
	public void persist(T t);
	
	public void setDefaultQueryObject(IQueryObject<T> query);
	
	public IQueryObject<T> getDefaultQueryObject();
	
	public void refresh(T t);
}
