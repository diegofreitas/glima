package br.com.sistemaweb.model;

import br.com.sistemaweb.domain.Fornecedor;
import br.com.sistemaweb.model.impl.GenericCrudModel;
import br.com.sistemaweb.persistence.FornecedorRepository;

public class FornecedorCrud extends GenericCrudModel<Fornecedor>{
	
	public FornecedorCrud() {
		super(new FornecedorRepository(),new Fornecedor());
	}
	//FIXME bodtao salvar adicionar deve ter mesmo comportamento de adicionar(menu de contexto)
}
