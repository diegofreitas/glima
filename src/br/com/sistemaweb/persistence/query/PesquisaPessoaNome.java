package br.com.sistemaweb.persistence.query;

import org.hibernate.Session;

import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.persistence.HibernateUtil;
import br.com.sistemaweb.persistence.query.SQLQueryObject;


public class PesquisaPessoaNome extends SQLQueryObject<Pessoa> {
	
	private String nome;
	
	private String endereco;
	
	private String asdf;
	
	public PesquisaPessoaNome() {
		super(Pessoa.class,"select nome from sistema.Pessoa as p  where p.nome like :nome and asdf=:asdf or  p.endereco like :endereco ");
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setAsdf(String asdf) {
		this.asdf = asdf;
	}

	public String getAsdf() {
		return asdf;
	}
	
	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

}
