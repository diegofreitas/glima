package br.com.sistemaweb.persistence;


import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.persistence.GenericRepository;



public class PessoaRepository extends GenericRepository<Pessoa> implements IPessoaRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = -969809538736808272L;

	public PessoaRepository() {
		super(Pessoa.class);
	}

	
	

}
