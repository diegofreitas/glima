package br.com.sistemaweb.persistence;

import br.com.sistemaweb.domain.Categoria;
import br.com.sistemaweb.persistence.GenericRepository;

public class CategoriaRepository extends GenericRepository<Categoria> implements ICategoriaRepository {

	public CategoriaRepository() {
		super(Categoria.class);
	}

}
