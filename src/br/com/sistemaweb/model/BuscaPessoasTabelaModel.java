/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 15/02/2009 12:25:40
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.persistence.query.IQueryObject;
import br.com.sistemaweb.persistence.query.PessoaQuery;
import br.com.sistemaweb.utils.Utils;

/**
 * @author Diego Lins de Freitas
 *
 */
public class BuscaPessoasTabelaModel implements IDependencyFieldModel<SelectItem> {
protected  final Logger log = LoggerFactory.getLogger(BuscaPessoaModel.class);
	
	private IQueryObject<?> query =new PessoaQuery();

	private Object specification;

	private List<SelectItem> list;
	
	/* (non-Javadoc)
	 * @see br.com.sistemaweb.model.IDependencyFieldModel#getQuery()
	 */
	
	/**
	 * 
	 */
	public BuscaPessoasTabelaModel() {
		search(null);
		specification=new Pessoa();
	}
	
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
	public List<SelectItem> getList() {
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
		list=(List<SelectItem>) Utils.createCollectionItems(query.list());
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.ISearch#setSpecification(java.lang.Object)
	 */
	@Override
	public void setSpecification(Object specification) {
		this.specification=specification;
	}
}


