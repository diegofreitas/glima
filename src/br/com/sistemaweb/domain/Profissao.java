/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 25/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
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
import br.com.sistemaweb.persistence.IProfissaoRepository;
import br.com.sistemaweb.persistence.IRepository;
import br.com.sistemaweb.persistence.ProfissaoRepository;

/**
 * @author Diego Lins de Freitas
 *
 */

@Entity
public class Profissao implements IDomainObject<Profissao,IProfissaoRepository> {

	private transient IProfissaoRepository repository;

	private Long id;
	private String descricao;
	private Double salarioBase;

	private Integer version;
	
	public Profissao(String descricao){
		this.descricao=descricao;
	}
	/**
	 * 
	 */
	public Profissao() {
	}
	
	
	
	public Profissao(IProfissaoRepository repository) {
		super();
		this.repository = repository;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.domainLayer.IDomainObject#getRepository()
	 */
	@Override
	@Transient
	public IProfissaoRepository getRepository() {
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
	public void setRepository(IProfissaoRepository repository) {
		this.repository=repository;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Profissao other = (Profissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id+" - "+descricao;
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
	public Profissao refresh() {
		repository.refresh(this);
		return this;
	}

}
