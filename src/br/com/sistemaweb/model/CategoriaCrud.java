package br.com.sistemaweb.model;

import java.io.Serializable;

import br.com.sistemaweb.domain.Categoria;
import br.com.sistemaweb.model.impl.GenericCrudModel;
import br.com.sistemaweb.persistence.CategoriaRepository;

public class CategoriaCrud extends GenericCrudModel<Categoria> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaCrud() {
		super(new CategoriaRepository(), new Categoria());
	}

}