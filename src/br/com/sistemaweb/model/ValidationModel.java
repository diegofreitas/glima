/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 31/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.utils.Utils;

/**
 * @author Diego Lins Freitas
 *
 */
@Deprecated
public class ValidationModel {

	
	
	protected final boolean isValid() {
		try {
			validateFields();
		} catch (ValidatorException e) {
			//Utils.getApplicationContext().addErrorMessage("Existem erros de validacao");//TODO colocar mensagem no messages.properties
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			//Utils.getApplicationContext().addErrorMessage("Ocorreu um erro inesperado: "+e.getMessage());//TODO colocar mensagem no messages.properties
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
				//Utils.getApplicationContext().addMessage("form:"+methodName.substring(8).toLowerCase(), e.getCause().getMessage());
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
				//Utils.getApplicationContext().addErrorMessage("Existem erros de validacao");//TODO colocar mensagem no messages.properties
				throw (ValidatorException) e.getCause();
			}
			e.printStackTrace();
		} 
	}
	


}
