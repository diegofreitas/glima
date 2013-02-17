package br.com.sistemaweb;

@SuppressWarnings("serial")
public class BusinessValidationException extends Exception {

	public BusinessValidationException(String id){
		super(id);
	}
}
