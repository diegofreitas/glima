package br.com.sistemaweb.test;

import java.util.List;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.persistence.query.PesquisaPessoaNome;



public class PesquisaPessoaNomeTest {

	private String nome;
	private String endereco;
	private String asdf;
	
	public PesquisaPessoaNomeTest(){
		
	}

	public String getNome() {
		return nome;
	}  

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Pessoa> listar(){
		PesquisaPessoaNome pesquisa = new PesquisaPessoaNome();
		pesquisa.setSpecification(this);
		return pesquisa.list();
	}
	  
	public static void main(String x[]){
		PesquisaPessoaNomeTest pesquisa = new PesquisaPessoaNomeTest();
		//pesquisa.setNome("diego");
		//pesquisa.setEndereco("DDLDLD");
		pesquisa.setAsdf("dddd");
		for(Pessoa p:pesquisa.listar()){
			System.out.println(p.getNome());
		}
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
	
	
}
