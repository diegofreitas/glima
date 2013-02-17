/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 13/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.persistence.query;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.IDomainObject;

/**
 * @author Diego Lins de freitas
 *
 *		Especializacao que implementa o comportamento principal de um query object 
 *	baseada em uma linguagem de consulta(HQL ou SQL nativo do banco de dados)
 */
@SuppressWarnings("unchecked")
public abstract class QueryLanguageObject<T> extends AbstractQueryObject<T> {

	private String qlExpression;

	private Map<String, Object> parameters = new HashMap<String, Object>();
	
	//TODO indicar na consulta qual where será dinamico
	private static final Pattern CONDITION_PATTERN = Pattern.compile(" +[a-zA-Z]*\\.?[a-zA-Z]+ *[=><like]+ *:[a-zA-Z]+ +[and]*[or]*");
	private static final Pattern  PARAMETER_PATTERN = Pattern.compile(":[a-zA-Z]+");
	private static final Pattern OPERATOR_PATTERN=Pattern.compile("[or]*[and]* *[a-zA-Z]");
	
	
	public QueryLanguageObject() {
		super();
	}

	public QueryLanguageObject(Class<T> classe) {
		super(classe);
	}

	
	public QueryLanguageObject(Class<T> classe, String qlExpression) {
		super(classe);
		this.qlExpression = qlExpression;
	}
	
	@Override
	protected List<T> executeQuery() {
		mapParameters();
		Query query=createQuery(getSession(), removeNullParameter());
		fillQuery(query);
		if(getClazz().isAssignableFrom(IDomainObject.class)){
			return (List<T>) query.list();
		}
		if(getClazz().isAssignableFrom(ResultTransformer.class)){
			try {
				query.setResultTransformer((ResultTransformer) getClazz().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		query.setResultTransformer(Transformers.aliasToBean(getClazz()));
		return (List<T>) query.list();
	}
	
	
	public void mapParameters() {
		try {
			parameters=PropertyUtils.describe(this);
			parameters.remove("session");
			parameters.remove("clazz");
			parameters.remove("specification");
			parameters.remove("qlExpression");
			parameters.remove("parameters");
			parameters.remove("class");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private String removeNullParameter() {
		String sql=qlExpression.toString();
		Matcher matcher = CONDITION_PATTERN.matcher(sql);
		while(matcher.find()){
			String condition=matcher.group();
			Matcher m = PARAMETER_PATTERN.matcher(condition);
	    	m.find();
	    	String parameter=m.group().substring(1);
	    	if(!parameters.containsKey(parameter) || parameters.get(parameter)==null){
				sql=sql.replace(condition," ");
				parameters.remove(parameter);
			}
		}
		if(parameters.isEmpty()){
			sql=sql.replace("where", " ");//e os "wheres" de subselects?????
		}
		return sql;
	}
	
	/**
	 * Metodo responsavel por setar os parametros da query 
	 */
	private void fillQuery(Query query) {
		for (String name : parameters.keySet()) {
			Object parameter = parameters.get(name);
			if (parameter != null) {
				try {
					query.setParameter(name, parameter);
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setQlExpression(final String qlExpression) {
		this.qlExpression = qlExpression;
	}

	public String getQlExpression() {
		return qlExpression;
	}
	
	public abstract Query createQuery(Session session,String query);
}
