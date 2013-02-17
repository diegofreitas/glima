package br.com.sistemaweb.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.IFornecedorRepository;
import br.com.sistemaweb.persistence.IRepository;


/**
 * @author Diego Lins de Freitas
 *
 */

@Entity
public class Fornecedor implements IDomainObject<Fornecedor, IFornecedorRepository> {

	private transient IFornecedorRepository repository;
	private Long id;
	private Integer version;

	private String nomeRazao;
	private String endereco;
	private String telefone;
	private String cpfCnpj;
	private String inscricaoRg;
	private String email;
	private TipoPessoa tipoPessoa;
	
	/**
	 * 
	 */
	public Fornecedor() {
	}
	
	public Fornecedor(TipoPessoa tipo) {
		this.tipoPessoa=tipo;
	}

	public Fornecedor(IFornecedorRepository repository) {
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

	
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getInscricaoRg() {
		return inscricaoRg;
	}

	public void setInscricaoRg(String inscricaoRg) {
		this.inscricaoRg = inscricaoRg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.domainLayer.IDomainObject#getRepository()
	 */
	@Override
	@Transient
	public IFornecedorRepository getRepository() {
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
	public void setRepository(IFornecedorRepository repository) {
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
		Fornecedor other = (Fornecedor) obj;
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
		return nomeRazao;
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
	public Fornecedor refresh() {
		repository.refresh(this);
		return this;
	}

}

