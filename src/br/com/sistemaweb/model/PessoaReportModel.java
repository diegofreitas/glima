/**
 * Este artefato de software foi desenvolvido por mim (root) em 19/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model;

import java.io.Serializable;

import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.model.impl.GenericReportModel;
import br.com.sistemaweb.persistence.query.CriteriaExampleQueryObject;

/**
 * @author root
 *
 */
public class PessoaReportModel extends GenericReportModel<Pessoa> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public PessoaReportModel() {
		super("pessoas",new CriteriaExampleQueryObject<Pessoa>(Pessoa.class));
		setSpecification(new Pessoa());
	}
	
	
}
