package br.com.sistemaweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.IEmpresaRepository;




@Entity
public class Empresa implements IDomainObject<Empresa,IEmpresaRepository>{


	private transient IEmpresaRepository repository;
	
	private Integer version;
	
	private Long id;
	private String fantasia;
	private String sistema;
	
	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	;
	
	
	public Empresa(){
		
	}
	
	public Empresa(IEmpresaRepository repository){
		this.repository=repository;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	
	@Override
	public void persist() {
		repository.persist(this);
	}
	
	@Override
	public void remove() throws Exception {
		repository.remove(this);
	}
	
	/* (non-Javadoc)
	 * @see br.com.sistemaweb.IDomainObject#refresh()
	 */
	@Override
	public Empresa refresh() {
		repository.refresh(this);
		return this;
	}

	@Override
	@Transient
	public  IEmpresaRepository getRepository() {
		return repository;
	}
	
	@Override
	public void setRepository(IEmpresaRepository repository) {
		this.repository=repository;	
	}
	
	
	@Override
	public String toString() {
		return String.format("%d - %s", id,fantasia);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSistema() {
		return sistema;
	}
	
	
	

	

	
	
}
