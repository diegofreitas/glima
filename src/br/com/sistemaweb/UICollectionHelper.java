/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 18/12/2008
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;

import br.com.sistemaweb.domain.*;
import br.com.sistemaweb.persistence.query.CriteriaQueryObject;
import br.com.sistemaweb.reporting.ContentType;
import br.com.sistemaweb.utils.Utils;


/**
 * @author Diego Lins de Freitas
 */
public class UICollectionHelper{

	private Collection<SelectItem> pessoas;
	
	private Collection<SelectItem> profissoes;
	
	private Collection<SelectItem> produtos;

	public Collection<SelectItem> getStatusCollection() {
		return Utils.createCollectionItems(Status.values());
	}

	public Collection<SelectItem> getPessoaCollection() {
		if (profissoes == null) {
			List<Pessoa> list = new ArrayList<Pessoa>();
			list.add(null);
			list.addAll(new CriteriaQueryObject<Pessoa>(Pessoa.class).list());
			profissoes = Utils.createCollectionItems(list);
		}
		return profissoes;
	}
	
	public Collection<Produto> getProdutos() {
		return	new CriteriaQueryObject<Produto>(Produto.class).list();
	}
	
	public Collection<SelectItem> getProfissaoCollection() {
		if (profissoes == null) {
			List<Profissao> list = new ArrayList<Profissao>();
			list.add(null);
			list.addAll(new CriteriaQueryObject<Profissao>(Profissao.class).list());
			profissoes = Utils.createCollectionItems(list);
		}
		return profissoes;
	}
	
	public Collection<SelectItem> getPessoas(){
		return Utils.createCollectionItems(new CriteriaQueryObject<Pessoa>(Pessoa.class).list());
	}
	
	public List<ContentType>  getExportTypes(){
		ArrayList<ContentType> arrayList = new ArrayList<ContentType>();
		arrayList.add(ContentType.PDF);
		arrayList.add(ContentType.XLS);
		arrayList.add(ContentType.RTF);
		arrayList.add(ContentType.SWF);
		return arrayList;
	}
	
	public Collection<SelectItem>  getTipoPessoa(){
		return Utils.createCollectionItems(TipoPessoa.values());
	}

	public Collection<SelectItem>  getUnidades(){
		return Utils.createCollectionItems(Unidade.values());
	}
}
