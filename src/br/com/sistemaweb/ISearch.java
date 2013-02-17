/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 05/02/2009 18:53:35
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb;

import java.util.List;

import javax.faces.event.ActionEvent;


/**
 * @author Diego Lins de Freitas
 *
 */
public interface ISearch<T> {
	
	public void search(ActionEvent e);
	
	public List<T> getList();
	
	public Object getSpecification();

	public void setSpecification(Object specification);

}


