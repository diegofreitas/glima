/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 25/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model;

import br.com.sistemaweb.ICrud;
import br.com.sistemaweb.ISearch;

/**
 * @author Diego Lins de Freitas
 */
public interface IController<T> extends ICrud<T>,ISearch<T>,IModel<T>{


}
