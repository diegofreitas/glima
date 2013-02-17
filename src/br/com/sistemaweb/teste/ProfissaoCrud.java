package br.com.sistemaweb.teste;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.sistemaweb.MessageFactory;
import br.com.sistemaweb.domain.Profissao;
import br.com.sistemaweb.persistence.ProfissaoRepository;
import br.com.sistemaweb.utils.Utils;

public class ProfissaoCrud implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PESQUISAR_STATE = "pesquisar";
	public static final String ADICIONAR_STATE = "adicionar";
	public static final String EDITAR_STATE = "editar";
	
	private String currentState = PESQUISAR_STATE;

	private String descricao;
	private Profissao profissao;
	private Collection<Profissao> profissaos = new ArrayList<Profissao>();
	private UIPanel panelForm;
	
	private MessageFactory messageFactory;
	
	public Collection<Profissao> getProfissaos() {
		return profissaos;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String nome) {
		this.descricao = nome;
	}
	public Profissao getProfissao() {
		if (profissao == null)
			this.profissao = new ProfissaoRepository().create();
		return profissao;
	}
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public UIPanel getPanelForm() {
		return panelForm;
	}
	public void setPanelForm(UIPanel panelForm) {
		this.panelForm = panelForm;
	}
	
	/**
	 * Pesquisa usuários por nome
	 */
	public void pesquisar() {
		this.setCurrentState(PESQUISAR_STATE);
		this.profissaos = new ProfissaoRepository().toCollection(new Profissao(this.descricao));
	}
	
	/**
	 * Prepara view adicionar
	 */
	public void prepareAdicionar() {
		this.clear();
		this.setCurrentState(ADICIONAR_STATE);
	}
	
	public void save(){
		if(!isValid())
			return;
		
		try {
		this.profissao.persist();
		getMessageFactory().addInfoMessage("Cadastro salvo com sucesso");
		} catch (Exception e) {
			getMessageFactory().addErrorMessage("Ocorreu um erro inesperado: "+e.getMessage());//TODO colocar mensagem no messages.properties
			e.printStackTrace();
		}
	}
	
	public void saveAndReturn(){
		save();
		this.pesquisar();
	}
	
	public void saveAndKeep(){
		save();
	}
	
	public void saveAndSelect(){
		save();
		this.voltar();
		
	}
	
	/**
	 * Prepara view editar
	 */
	public void prepareEditar() {
		this.setCurrentState(EDITAR_STATE);
	}
	
	/**
	 * Exclui usuário
	 */
	public void excluir() {
		try {
			this.profissao.remove();
			this.pesquisar();
			getMessageFactory().addInfoMessage(profissao +"excluído do sistema");
		} catch (ConstraintViolationException e) {
			getMessageFactory().addErrorMessage("erro.constraint.violation");
		} catch (Exception e) {
			getMessageFactory().addErrorMessage("Ocorreu um erro inesperado: "+e.getMessage());
		}
	}
	
	/**
	 * Referente ao botão voltar
	 */
	public void voltar() {
		this.clear();
		this.pesquisar();
	}
	
	/**
	 * Limpa atributos
	 */
	private void clear() {
		this.descricao = "";
		this.profissao = new ProfissaoRepository().create();
		this.cleanSubmittedValues(this.panelForm);
	}
	
	/*
	 * Métodos que expõem o estado à página
	 */
	
	public boolean isPesquisarState() {
		String state = this.getCurrentState();
		return (state == null || PESQUISAR_STATE.equals(state));
	}
	public boolean isAdicionarState() {
		return ADICIONAR_STATE.equals(this.getCurrentState());
	}
	public boolean isEditarState() {
		return EDITAR_STATE.equals(this.getCurrentState());
	}
	

	
	/**
	 * Limpa os componentes filhos para que depois eles possam ser recriados
	 * @param component
	 */
	protected void cleanSubmittedValues(UIComponent component) {
		component.getChildren().clear();
	}
	
	
	
	/////////////////////////////////////////////////////////////////

	protected final boolean isValid() {
		try {
			validateFields();
		} catch (ValidatorException e) {
			getMessageFactory().addErrorMessage("Existem erros de validacao");//TODO colocar mensagem no messages.properties
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			getMessageFactory().addErrorMessage("Ocorreu um erro inesperado: "+e.getMessage());//TODO colocar mensagem no messages.properties
			return false;
		}
		return true;
	}
	
	private void validateFields() throws ValidatorException, Exception {
		String methodName = "";
		try {
			for(Method met:this.getClass().getMethods()){
				methodName=met.getName();
				if(methodName.startsWith("validate") && met.getParameterTypes().length ==0 && !methodName.contains("Field")){
					met.invoke(this, (Object[])null);
				}
			}
		} catch (InvocationTargetException e) {
			if(e.getCause() instanceof ValidatorException){
				getMessageFactory().addMessage("form:"+methodName.substring(8).toLowerCase(), e.getCause().getMessage());
				throw (ValidatorException) e.getCause();
			}else{
				throw e;
			}
		} 
		
	}
	
	public final void validateField(FacesContext ctx,UIComponent ui,Object value) throws ValidatorException{
		
		try {
			String validateMethod="validate"+Utils.captalizar(ui.getId());
			for(Method met:this.getClass().getMethods()){
				if(met.getName().equals(validateMethod)){
					met.invoke(this, value);
				}
			}
			throw new ValidatorException(new FacesMessage(String.format("O metodo %s nao foi definido para o campo %s",validateMethod,ui.getId())));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			if(e.getCause() instanceof ValidatorException){
				getMessageFactory().addErrorMessage("Existem erros de validacao");//TODO colocar mensagem no messages.properties
				throw (ValidatorException) e.getCause();
			}
			e.printStackTrace();
		} 
	}
	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}
	public MessageFactory getMessageFactory() {
		if(messageFactory==null)
			messageFactory=new MessageFactory();
		return messageFactory;
	}
	
	
	
	
	
	
}
