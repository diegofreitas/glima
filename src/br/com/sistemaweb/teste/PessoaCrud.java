package br.com.sistemaweb.teste;

import java.io.Serializable;

import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.domain.Profissao;
import br.com.sistemaweb.model.impl.GenericCrudModel;
import br.com.sistemaweb.persistence.PessoaRepository;

public class PessoaCrud extends GenericCrudModel<Pessoa> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PessoaCrud() {
		super(new PessoaRepository(),new Pessoa());
	}
	
	
	public void setProfissao(Profissao profissao){
		this.getEntity().setProfissao(profissao);
	}
	
}
