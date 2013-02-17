/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 13/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.persistence.query;
import java.util.List;

import org.hibernate.Criteria;

/**
 * 
 * @author Diego Lins de Freitas
 * 
 * Especializacao para criar consultas com uma Criteria 
 * nesta classe o atributo clazz(que deve ser uma entidade de negocio assim como o parametro generico) tem como finalidade
 * ser usado para criar a Criteria do hibernate 
 *
 * @param <T>
 */

@SuppressWarnings("unchecked")
public class CriteriaQueryObject<T> extends AbstractQueryObject<T> {

	/**
	 * Criteria do Hibernate
	 */
	private Criteria criteria;
	
	public CriteriaQueryObject(){

	}
	
	public CriteriaQueryObject(Class<T> clazz){
		super(clazz);
	}

	/**
	 * Metodo responsavel por criar a criteria
	 * @return
	 */
	public final Criteria getCriteria() {
		if(criteria==null){
			criteria=getSession().createCriteria(getClazz());
		}
		return criteria;	
	}
	/**
	 * Metodo responsavel por executar a consulta
	 */
	@Override
	protected final List<T> executeQuery() {
		List<T> list = (List<T>)getCriteria().list();
		criteria=null;
		return list;
	}
	

}
