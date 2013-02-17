package br.com.sistemaweb.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.persistence.IPessoaRepository;
import br.com.sistemaweb.persistence.IRepository;




@Entity
public class Pessoa implements IDomainObject<Pessoa,IPessoaRepository>{

	

	private transient IPessoaRepository repository;
	
	private Integer version;
	
	private Long id;
	private String nome;
	private Date nascimento;
	private String endereco;
	private Profissao profissao;
	private String telefone;
	private Status status;
	private Pessoa pai;
	private String comentario;
	
	public Pessoa(){
		
	}
	
	public Pessoa(IPessoaRepository repository){
		this.repository=repository;
	}
	
	public Pessoa(String nome){
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

	@Temporal(TemporalType.DATE)
	public Date getNascimento() {
		return nascimento;
	}
	
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setPai(Pessoa pai) {
		this.pai = pai;
	}

	@OneToOne(fetch=FetchType.LAZY)
	public Pessoa getPai() {
		return pai;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return comentario;
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
	public Pessoa refresh() {
		repository.refresh(this);
		return this;
	}

	@Override
	@Transient
	public  IPessoaRepository getRepository() {
		return repository;
	}
	
	@Override
	public void setRepository(IPessoaRepository repository) {
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
		Pessoa other = (Pessoa) obj;
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

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	public Profissao getProfissao() {
		return profissao;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Version
	public Integer getVersion() {
		return version;
	}


	
	
	

	

	
	
}
