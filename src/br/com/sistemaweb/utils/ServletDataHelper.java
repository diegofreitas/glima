package br.com.sistemaweb.utils;

import br.com.sistemaweb.reporting.ContentType;

/**
 * Classe que vai auxiliar o envio dos dados pela ServletData
 * encapsulando os dados
 * 
 * @author diego
 *
 */
public class ServletDataHelper {
	
	private String name;
	private ContentType exportType;
	private byte[] stream;

	public ServletDataHelper() {}

	public ServletDataHelper(String name, ContentType exportType,byte[] stream) {
		this.setName(name);
		this.setExportType(exportType);
		this.setStream(stream);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setExportType(ContentType exportType) {
		this.exportType = exportType;
	}

	public ContentType getExportType() {
		return exportType;
	}

	public void setStream(byte[] stream2) {
		this.stream = stream2;
	}

	public byte[] getStream() {
		return stream;
	}

}
