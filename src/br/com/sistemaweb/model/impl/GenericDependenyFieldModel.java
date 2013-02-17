/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 07/02/2009 21:33:08
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model.impl;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.model.IDependencyFieldModel;
import br.com.sistemaweb.persistence.IRepository;
import br.com.sistemaweb.persistence.query.IQueryObject;

/**
 * @author Diego Lins de Freitas
 *
 */
public class GenericDependenyFieldModel implements IDependencyFieldModel<IDomainObject<?,? extends IRepository<?>>> {
	
	protected  final Logger log = LoggerFactory.getLogger(GenericDependenyFieldModel.class);
	
	private IQueryObject<?> query;

	private Object specification;

	private List<IDomainObject<?,? extends IRepository<?>>> list;
	
	/* (non-Javadoc)
	 * @see br.com.sistemaweb.model.IDependencyFieldModel#getQuery()
	 */
	@Override
	public IQueryObject<?> getQuery() {
		return query;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.model.IDependencyFieldModel#setQuery(br.com.sistemaweb.persistence.query.IQueryObject)
	 */
	@Override
	public void setQuery(IQueryObject<?> query) {
		this.query=query;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.ISearch#getList()
	 */
	@Override
	public List<IDomainObject<?,? extends IRepository<?>>> getList() {
		return  list;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.ISearch#getSpecification()
	 */
	@Override
	public Object getSpecification() {
		return specification;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.ISearch#search(javax.faces.event.ActionEvent)
	 */
	@Override
	public void search(ActionEvent e) {
		query.setSpecification(specification);
		list=(List<IDomainObject<?,? extends IRepository<?>>>) query.list();
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.ISearch#setSpecification(java.lang.Object)
	 */
	@Override
	public void setSpecification(Object specification) {
		this.specification=specification;
	}
}


