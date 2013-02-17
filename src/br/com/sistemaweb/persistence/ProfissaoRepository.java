package br.com.sistemaweb.persistence;

import br.com.sistemaweb.domain.Profissao;
import br.com.sistemaweb.persistence.GenericRepository;



public class ProfissaoRepository extends GenericRepository<Profissao> implements IProfissaoRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfissaoRepository() {
		super(Profissao.class);
	}
	
	

}
