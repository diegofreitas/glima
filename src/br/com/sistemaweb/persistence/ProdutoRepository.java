package br.com.sistemaweb.persistence;

import br.com.sistemaweb.domain.Produto;
import br.com.sistemaweb.persistence.GenericRepository;

public class ProdutoRepository extends GenericRepository<Produto> implements
		IProdutoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4498198961138326469L;

	public ProdutoRepository() {
		super(Produto.class);
	}

}

