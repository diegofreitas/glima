package br.com.sistemaweb.model;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.validator.ValidatorException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.richfaces.event.UploadEvent;

import br.com.sistemaweb.domain.Produto;
import br.com.sistemaweb.model.impl.GenericCrudModel;
import br.com.sistemaweb.persistence.ProdutoRepository;
import br.com.sistemaweb.reporting.ContentType;
import br.com.sistemaweb.utils.ServletDataHelper;
import br.com.sistemaweb.utils.Utils;

public class ProdutoCrud extends GenericCrudModel<Produto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoCrud() {
		super(new ProdutoRepository(), new Produto());
	}

	public void upload(UploadEvent event){
		getEntity().setFoto( event.getUploadItem().getData());
	}
	
	public String getIdImg(){
		String name = getEntity().toString();
		Utils.saveToSession(name, null, getEntity().getFoto());
		return name;
	}

	public void validateQuantidade(Object q){
		Double d=(Double) q;
		if(d.doubleValue()<=0){
			throw new ValidatorException(getMessageFactory().getResourceBundleMessage("produtoQuantidadeInvalida"));
		}
	}
	
	public void validatePrecoVenda(Object q){
		Double d=(Double) q;
		if(d.doubleValue()<=0){
			throw new ValidatorException(getMessageFactory().getResourceBundleMessage("produtoPrecoVendaInvalida"));
		}
	}
	
	public void validateCategoria(Object c){
		if(c==null){
			throw new ValidatorException(getMessageFactory().getResourceBundleMessage("javax.faces.component.UIInput.REQUIRED"));
		}
	}
	


}