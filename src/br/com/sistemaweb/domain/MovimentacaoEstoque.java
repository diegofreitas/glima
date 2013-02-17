package br.com.sistemaweb.domain;

import java.io.Serializable;

import javax.faces.event.ValueChangeEvent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CascadeType;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.ICategoriaRepository;
import br.com.sistemaweb.persistence.IMovimentacaoEstoqueRepository;

@Entity
public class MovimentacaoEstoque implements IDomainObject<MovimentacaoEstoque,IMovimentacaoEstoqueRepository> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient IMovimentacaoEstoqueRepository repository;
	

	private Integer version;
	
	
	private Long id;
	private Produto produto;
	private double entrada;
	private double saida;
	private double total;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.PERSIST)
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getEntrada() {
		return entrada;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}

	public double getSaida() {
		return saida;
	}

	public void setSaida(double saida) {
		this.saida = saida;
	}

	public MovimentacaoEstoque() {

	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (produto == null) {
			return super.equals(obj);
		}
		return produto.equals(((MovimentacaoEstoque) obj).getProduto());
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

	



	@Override
	public void persist() {
		produto.setQuantidade(produto.getQuantidade() - saida + entrada);
		produto.persist();
		repository.persist(this);
	}

	@Override
	public MovimentacaoEstoque refresh() {
		repository.refresh(this);
		return this;
	}

	@Override
	public void remove() throws Exception {
		repository.remove(this);
		
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}
	
	@Transient
	public IMovimentacaoEstoqueRepository getRepository() {
		return repository;
	}

	public void setRepository(IMovimentacaoEstoqueRepository repository) {
		this.repository = repository;
	}
	
}
