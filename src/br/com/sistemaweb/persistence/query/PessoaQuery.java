/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 07/02/2009 23:04:08
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.persistence.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sistemaweb.domain.Pessoa;

/**
 * @author Diego Lins de Freitas
 *
 */
public class PessoaQuery extends CriteriaExampleQueryObject<Pessoa> {
	
	private static final Logger log = LoggerFactory
	.getLogger(PessoaQuery.class);
	
	/**
	 * @param clazz
	 */
	public PessoaQuery() {
		super(Pessoa.class);
		log.info("criando query", this);
	}

	
}


