package br.com.sistemaweb.persistence;

import br.com.sistemaweb.domain.MovimentacaoEstoque;

public class MovimentacaoEstqueRepository extends GenericRepository<MovimentacaoEstoque> implements IMovimentacaoEstoqueRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovimentacaoEstqueRepository(){
		super(MovimentacaoEstoque.class);
	}
}
