package br.com.sistemaweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.ICategoriaRepository;

@Entity
public class Categoria implements IDomainObject<Categoria, ICategoriaRepository> {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8836153356461218401L;
	
	private transient ICategoriaRepository repository;
	private Integer version;
	
	private Long id;
	private String descricao;
	
	public Categoria(){
		
	}
	
	public Categoria(ICategoriaRepository resp){
		repository=resp;
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

	@Transient
	public ICategoriaRepository getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Version
	public Integer getVersion() {
		// TODO Auto-generated method stub
		return version;
	}

	@Override
	public void persist() {
		repository.persist(this);
		
	}

	@Override
	public Categoria refresh() {
		repository.refresh(this);
		return this;
	}

	@Override
	public void remove() throws Exception {
		repository.remove(this);
	}

	@Override
	public void setRepository(ICategoriaRepository repository) {
		this.repository=repository;
		
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return descricao;
	}
	 
}
 
