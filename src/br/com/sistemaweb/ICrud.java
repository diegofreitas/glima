/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 05/02/2009 18:53:48
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb;

import javax.faces.event.ActionEvent;

/**
 * @author Diego Lins de Freitas
 *
 */
public interface ICrud<T> {

	public String save();

	public String cancel();

	public String create() throws Exception;

	public void retrieve(ActionEvent event);

	public void delete(ActionEvent event);

	public Class<T> getDomainClass();

	public void setDomainClass(Class<T> classe);

	public T getEntity();

	public void setEntity(T entity);
}


