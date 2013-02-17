/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 30/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.MessageFactory;

/**
 * @author Diego Lins de Freitas
 *
 *	Classe responsavel por executar um download recebendo um outputstream. 
 */
public class Download {
	
	private static Logger log=LoggerFactory.getLogger(Download.class);
	
	/**
	 * Representa os dados a serem baixados.
	 */
	private ByteArrayOutputStream outputStream;
	
	/**
	 * Define o tipo de conteudo dos dados, eh bom defini-lo para que o browser saibar o que fazer quando receber os dados.
	 */
	private String contentType="application/octet-stream";
	
	/**
	 * Nome do arquivo 
	 */
	private String fileName;

	private MessageFactory messageFactory;
	
	/**
	 * Metodo responsavel por colocar os dados no ServletResponse e 
	 * finalizar o contexto jsf para garantir que sera enviado os dados do download. 
	 */
	public void send(){
		if(outputStream==null)
			throw new IllegalArgumentException("outputstream is null, you must set the outputStream attribute before call Download#send");
			
		HttpServletResponse response = (HttpServletResponse) Utils.getApplicationContext().getFacesContext().getExternalContext().getResponse();
		OutputStream responseOutputStream = null;
		try {
			response.setContentType(contentType);
			int length = outputStream.toByteArray().length;
			response.setContentLength(length);
			response.setHeader("Content-Disposition", "attachment; filename="+fileName);
			responseOutputStream = response.getOutputStream();
			responseOutputStream.write(outputStream.toByteArray(), 0, length);
			responseOutputStream.flush();
			Utils.getApplicationContext().getFacesContext().responseComplete();
		} catch (IOException e) {
			getMessageFactory().addErrorMessage("download.error"); //TODO colocar no properties
			log.error("Exception in send", e);
		}finally{
			try {
				outputStream.close();
				responseOutputStream.close();
			} catch (IOException e) {
				log.error("Exception in send", e);
				getMessageFactory().addErrorMessage("download.error");
			}
			
		}
	}


	public OutputStream getOutputStream() {
		return outputStream;
	}


	public void setOutputStream(ByteArrayOutputStream outputStream) {
		this.outputStream = outputStream;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MessageFactory getMessageFactory() {
		if(messageFactory== null)
			messageFactory=new MessageFactory();
		
		return messageFactory;
	}
}
