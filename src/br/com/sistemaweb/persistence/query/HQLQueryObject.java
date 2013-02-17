package br.com.sistemaweb.persistence.query;

import org.hibernate.Query;
import org.hibernate.Session;

public class HQLQueryObject<T> extends QueryLanguageObject<T> {

	
	
	public HQLQueryObject() {
		super();
	}

	public HQLQueryObject( String sqlExpression) {
		setQlExpression(sqlExpression);
	}

	public HQLQueryObject(Class<T> classe, String sqlExpression) {
		super(classe, sqlExpression);
	}

	public HQLQueryObject(Class<T> classe) {
		super(classe);
	}

	@Override
	public Query createQuery(Session session, String query) {
		return session.createQuery(query);
	}

}
