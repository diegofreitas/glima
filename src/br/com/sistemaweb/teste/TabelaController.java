/**
 * Este artefato de software foi desenvolvido por mim (root) em 04/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.teste;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import br.com.sistemaweb.domain.Pessoa;
import br.com.sistemaweb.security.Usuario;
import br.com.sistemaweb.utils.ParentChildFormSupport;
import br.com.sistemaweb.utils.Utils;

/**
 * @author root
 *
 */
public class TabelaController {
	
	private List<Usuario> usuarios =new ArrayList<Usuario>();
	
	private List<Pessoa> pessoas =new ArrayList<Pessoa>();

	private ParentChildFormSupport<Usuario> PCFSupport=new ParentChildFormSupport<Usuario>(Usuario.class);
	/**
	 * 
	 */
	public TabelaController() {		
		getUsuarios().add(new Usuario("DIego","asdideass"));
		getUsuarios().add(new Usuario("asdfasfa","asdideass"));	
	}
	

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		if(PCFSupport.getChildren()==null){
			PCFSupport.setChildren(usuarios);
		}
		return usuarios;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPCFSupport(ParentChildFormSupport<Usuario> pCFSupport) {
		PCFSupport = pCFSupport;
	}

	public ParentChildFormSupport<Usuario> getPCFSupport() {
		return PCFSupport;
	}
	
	public int getTotalPessoas(){
		return pessoas.size();
	}

	
}
