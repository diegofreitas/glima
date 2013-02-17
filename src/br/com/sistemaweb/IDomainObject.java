package br.com.sistemaweb;

import java.io.Serializable;

import br.com.sistemaweb.persistence.IRepository;


public interface IDomainObject<T,R> extends Serializable{

	public void setRepository(R repository);
	
	public R getRepository();
	
	public void remove() throws Exception;
	
	public void persist();
	
	public T refresh();
	
	public Integer getVersion();
	
}
