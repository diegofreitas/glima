package br.com.sistemaweb.model.impl;


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

import br.com.sistemaweb.IDomainObject;
import br.com.sistemaweb.MessageFactory;
import br.com.sistemaweb.persistence.IRepository;
import br.com.sistemaweb.utils.Utils;

public class GenericCrudModel<T extends IDomainObject> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PESQUISAR_STATE = "pesquisar";
	public static final String ADICIONAR_STATE = "adicionar";
	public static final String EDITAR_STATE = "editar";
	
	
	private String currentState = PESQUISAR_STATE;

	private T entity;
	private Collection<T> entities = new ArrayList<T>();
	private UIPanel panelForm;
	private Object specification;

	private IRepository<T> repository;
	private transient MessageFactory messageFactory;
	
	public T getEntity() {
		if (entity == null)
			this.entity = repository.create();
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public Object getSpecification() {
		return specification;
	}
	public void setSpecification(Object specfication) {
		this.specification = specfication;
	}
	public IRepository<T> getRepository() {
		return repository;
	}
	public void setRepository(IRepository<T> repository) {
		this.repository = repository;
	}
	public void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}

	
	
	public GenericCrudModel() {
		
	}
	
	public GenericCrudModel(IRepository<T> repos){
		repository=repos;
	}
	
	
	public GenericCrudModel(IRepository<T> repos,Object specification){
		repository=repos;
		this.specification=specification;
		pesquisar();
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
		this.setEntities(repository.toCollection(specification));
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
		this.entity.persist();
		getMessageFactory().addInfoMessage("Cadastro salvo com sucesso");
		} catch (Exception e) {
			getMessageFactory().addErrorMessage("Ocorreu um erro inesperado: "+e.getMessage());//TODO colocar mensagem no messages.properties
			e.printStackTrace();
		}
	}
	
	public void saveAndReturn(){
		save();
		this.entity=null;
		this.pesquisar();
	}
	
	public void saveAndKeep(){
		save();
	}
	
	public void saveAndSelect(){
		save();
		this.voltar();
		
	}
	
	public void saveAndAdd(){
		save();
		prepareAdicionar();
	}
	
	/**
	 * Prepara view editar
	 */
	public void prepareEditar() {
		entity.refresh();
		this.setCurrentState(EDITAR_STATE);
	}
	
	/**
	 * Exclui usuário
	 */
	public void excluir() {
		try {
			this.entity.remove();
			this.pesquisar();
			getMessageFactory().addInfoMessage(entity +" excluído do sistema");
		} catch (ConstraintViolationException e) {
			getMessageFactory().addErrorMessage("erro.constraint.violation");
			e.printStackTrace();
		} catch (Exception e) {
			getMessageFactory().addErrorMessage("Ocorreu um erro inesperado: "+e.getMessage());
			e.printStackTrace();
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
		try {
			specification=specification.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.entity = repository.create(); 
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
	
	public MessageFactory getMessageFactory() {
		if(messageFactory== null)
			messageFactory=new MessageFactory();
		
		return messageFactory;
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
					return;
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
	public void setEntities(Collection<T> entities) {
		this.entities = entities;
	}
	public Collection<T> getEntities() {
		return entities;
	}
	
	
	
	
	
	
	
}
