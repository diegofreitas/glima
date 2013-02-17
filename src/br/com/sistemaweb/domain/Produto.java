package br.com.sistemaweb.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.IProdutoRepository;
import br.com.sistemaweb.persistence.IRepository;
import br.com.sistemaweb.persistence.ProdutoRepository;

/**
 * @author Diego Lins de Freitas
 *
 */

@Entity
public class Produto implements IDomainObject<Produto, IProdutoRepository> {

	private transient IProdutoRepository repository;
	private Long id;
	private Integer version;
	
	private String referencia;
	private String descricao;
	private byte[] foto;
	private Unidade unidade;
	private double quantidade;
	private double quantMinima;
	private double precoVenda;
	private double precoCompra;
	private double lucro;
	private String observacao;
	private Fornecedor fornecedor;
	private Categoria categoria;
	
	public Produto(String descricao){
		this.descricao= descricao;
	}
	
	@ManyToOne
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	@ManyToOne
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Lob 
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getQuantMinima() {
		return quantMinima;
	}

	public void setQuantMinima(double quantMinima) {
		this.quantMinima = quantMinima;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	/**
	 * 
	 */
	public Produto() {
	}

	public Produto(IProdutoRepository repository) {
		super();
		this.repository = repository;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.domainLayer.IDomainObject#getRepository()
	 */
	@Override
	@Transient
	public IProdutoRepository getRepository() {
		return repository;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.domainLayer.IDomainObject#persist()
	 */
	@Override
	public void persist() {
		repository.persist(this);
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.domainLayer.IDomainObject#remove()
	 */
	@Override
	public void remove() throws Exception {
		repository.remove(this);
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.domainLayer.IDomainObject#setRepository(br.com.sistemaweb.domainLayer.IRepository)
	 */
	@Override
	public void setRepository(IProdutoRepository repository) {
		this.repository = repository;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id == null) ? super.hashCode() : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return super.equals(obj);
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(id==null){
			return super.toString();
		}
		return id + " - " + descricao;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.IDomainObject#refresh()
	 */
	@Override
	public Produto refresh() {
		repository.refresh(this);
		return this;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacao() {
		return observacao;
	}

}

 
