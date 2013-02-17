package br.com.sistemaweb.persistence.query;

import org.hibernate.Query;
import org.hibernate.Session;

public class NamedQueryObject<T> extends QueryLanguageObject<T> {

	private String namedQuery;
	
	public NamedQueryObject(String namedQuery){
		this.namedQuery=namedQuery;
	}
	
	public NamedQueryObject() {
		super();
	}

	public NamedQueryObject(Class<T> classe) {
		super(classe);
	}
	public NamedQueryObject(Class<T> classe,String namedQuery) {
		super(classe);
		this.namedQuery=namedQuery;
	}

	public String getNamedQuery() {
		return namedQuery;
	}
	public void setNamedQuery(String namedQuery) {
		this.namedQuery = namedQuery;
	}

	@Override
	public Query createQuery(Session session, String query) {
		return session.getNamedQuery(namedQuery);
	}

}
