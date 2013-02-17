/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 13/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.persistence.query;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.HibernateUtil;
import br.com.sistemaweb.persistence.IRepository;
import br.com.sistemaweb.utils.Utils;

/**
 * @author Diego Lins de freitas
 *
 *	Classe abstrata que implementa o comportamento basico de uma query object
 */
public abstract class AbstractQueryObject<T>  implements IQueryObject<T> {

	/**
	 * Sessao do hibernate
	 */
	private Session session;

	/**
	 * Define a classe que sera usada de acordo com o tipo de query object(criar uma Criteria , encapsular os resutados...
	 */
	private Class<T> clazz;
	
	/**
	 * Objeto cujo seus atributos sao os parametros da consulta Ex:
	 * 
	 * Specfication para a query object PesquisaEntidadePeloNome que precisa de um parametro para o nome;
	 * 
	 * public class PesquisaEntidadePeloNomeSpecification{
	 * 		
	 * 		private String nome;
	 * 
	 * 		//getter e setter
	 * 			...
	 * }
	 * 
	 */
	private Object specification;

	
	public AbstractQueryObject() {
		super();
		
	}

	public AbstractQueryObject(Class<T> classe) {
		this();
		this.clazz=classe;
		
	}
	

	public Object getSpecification() {
		return specification;
	}

	public void setSpecification(Object specification) {
		this.specification = specification;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Metodo que retorna um List com os resultados
	 */
	public  List<T> list() {
		if(specification!=null){
			setParametersFromSpecification(specification);
		}
		List<T> list=executeQuery();
		return list;
	}

	/**
	 * Metodo responsavel por executar a consulta de acordo com a sua implementacao
	 * 
	 * @return List<T>
	 */
	protected abstract List<T> executeQuery() ;
	
	/**
	 * Responsavel por copiar os atributos de um specification para o query object
	 * @param bean
	 */
	protected void setParametersFromSpecification(Object bean) {
		try {
			
				PropertyUtils.copyProperties(this, bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Session getSession() {
		return session=Utils.getApplicationContext().getHibernateSession();
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
