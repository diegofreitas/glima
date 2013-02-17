package br.com.sistemaweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.IFuncionarioRepository;
import br.com.sistemaweb.persistence.IPermissaoRepository;
import br.com.sistemaweb.persistence.IPessoaRepository;




@Entity
public class Permissao implements IDomainObject<Permissao,IPermissaoRepository>{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient IPermissaoRepository repository;
	
	private Integer version;
	
	private Long id;
	private String login;
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	private String permissao;
	
	public Permissao(){
		
	}
	
	public Permissao(IPermissaoRepository repository){
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
	public Permissao refresh() {
		repository.refresh(this);
		return this;
	}

	@Override
	@Transient
	public  IPermissaoRepository getRepository() {
		return repository;
	}
	
	@Override
	public void setRepository(IPermissaoRepository repository) {
		this.repository=repository;	
	}
	
	
	@Override
	public String toString() {
		return String.format("%d - %s", id,permissao);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissao other = (Permissao) obj;
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


	
	
	

	

	
	
}
