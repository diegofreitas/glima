package br.com.sistemaweb.persistence.query;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

public class CriteriaExampleQueryObject<T> extends CriteriaQueryObject<T>{
	
	
	public CriteriaExampleQueryObject(){
		
	}
	public CriteriaExampleQueryObject(Class<T> clazz) {
		super(clazz);
	}

	public void setExample(T example){
		Example criterion = Example.create(example).ignoreCase().enableLike(
				MatchMode.ANYWHERE).excludeProperty("repository").excludeProperty("version")
				.excludeZeroes();
		
		getCriteria().add(criterion);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sistemaweb.persistenceLayer.query.AbstractQueryObject#setParametersFromSpecification(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setParametersFromSpecification(Object bean) {
		setExample((T) bean);
	}
	

}
