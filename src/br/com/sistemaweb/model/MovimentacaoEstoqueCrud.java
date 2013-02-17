package br.com.sistemaweb.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import br.com.sistemaweb.domain.MovimentacaoEstoque;
import br.com.sistemaweb.domain.Produto;
import br.com.sistemaweb.model.impl.GenericCrudModel;
import br.com.sistemaweb.persistence.MovimentacaoEstqueRepository;
import br.com.sistemaweb.persistence.ProdutoRepository;

public class MovimentacaoEstoqueCrud extends GenericCrudModel<MovimentacaoEstoque> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovimentacaoEstoqueCrud() {
		super(new MovimentacaoEstqueRepository(), new MovimentacaoEstoque());
	}
	
	public List<Produto> autoCompleteProdutos(Object prefixo){
		ProdutoRepository resp = new ProdutoRepository();
		return (List<Produto>) resp.toCollection(new Produto(prefixo.toString())); 
	}
	
	public void produtoSelecionado(ValueChangeEvent evt){
		
	}

}