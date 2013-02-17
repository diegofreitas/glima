package br.com.sistemaweb.persistence;

import br.com.sistemaweb.domain.Fornecedor;
import br.com.sistemaweb.domain.TipoPessoa;

public class FornecedorRepository extends GenericRepository<Fornecedor> implements IFornecedorRepository{
	
	public FornecedorRepository(){
		super(Fornecedor.class);
	}

	@Override
	public final Fornecedor create() {
		Fornecedor f =super.create();
		f.setTipoPessoa(TipoPessoa.FISICA);
		return f;
	}
}
