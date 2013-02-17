/**
 * Este artefato de software foi desenvolvido por mim (root) em 16/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model;

import java.util.List;

import javax.faces.event.ActionEvent;

import br.com.sistemaweb.ISearch;
import br.com.sistemaweb.persistence.query.IQueryObject;

/**
 * @author root
 *
 */
public interface IDependencyFieldModel<T> extends ISearch<T>{

	public IQueryObject<?> getQuery();
	
	public void setQuery(IQueryObject<?> query);

}
