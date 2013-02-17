/**
 * Este artefato de software foi desenvolvido por mim (root) em 02/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author root
 *
 */

@Entity
public class Usuario implements IUser {
	
	private Long id;
	private String nome;
	private String senha;
	private String permissoes;
	
	/**
	 * 
	 */
	public Usuario() {
	}
	
	public Usuario(String nome, String senha) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.permissoes=" listaProfissao listaPessoa ";
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenha() {
		return senha;
	}
	public void setPermissoes(String permissoes) {
		this.permissoes = permissoes;
	}
	public String getPermissoes() {
		return permissoes;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
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
		return nome;
	}
	
}
