package br.com.sistemaweb.persistence.query;

import org.hibernate.Query;
import org.hibernate.Session;

public class SQLQueryObject<T> extends QueryLanguageObject<T> {

	public SQLQueryObject(Class<T> bean, final String sqlExpression) {
		super(bean);
		this.setQlExpression(sqlExpression);
	}

	public SQLQueryObject(Class<T> classe) {
		super(classe);
	}

	@Override
	public Query createQuery(Session session, String query) {
		return session.createSQLQuery(query);
	}

}
