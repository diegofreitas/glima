package br.com.sistemaweb.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.IFuncionarioRepository;




@Entity
public class Funcionario implements IDomainObject<Funcionario,IFuncionarioRepository>{


	private transient IFuncionarioRepository repository;
	
	private Integer version;
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private Empresa empresa;
	
	public Funcionario(){
		
	}
	
	public Funcionario(IFuncionarioRepository repository){
		this.repository=repository;
	}
	
	public Funcionario(String nome){
		this.nome=nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	@OrderBy
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Funcionario refresh() {
		repository.refresh(this);
		return this;
	}

	@Override
	@Transient
	public  IFuncionarioRepository getRepository() {
		return repository;
	}
	
	@Override
	public void setRepository(IFuncionarioRepository repository) {
		this.repository=repository;	
	}
	
	
	@Override
	public String toString() {
		return String.format("%d - %s", id,nome);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
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

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}


	
	
	

	

	
	
}
