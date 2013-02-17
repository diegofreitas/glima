/**
 * Este artefato de software foi desenvolvido por mim (root) em 27/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfEncryption;

/**
 * @author root
 * 
 */
public class Html2Pdf {

	private String htmlPath;
	
	
	public  void convert(String input, OutputStream out)throws DocumentException, IOException {
		convert(new ByteArrayInputStream(input.getBytes()), out);
	}

	public  void convert(InputStream input, OutputStream output)throws DocumentException, IOException {
		Tidy tidy = new Tidy();
		Document doc = tidy.parseDOM(input, null);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(doc, null);
		renderer.layout();
		renderer.createPDF(output);
		output.flush();
		output.close();
	}
	
	public  void convert(OutputStream output)throws DocumentException, IOException {
		convert(new FileInputStream(new File(htmlPath)), output);
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}

	public String getHtmlPath() {
		return htmlPath;
	}


	
}
