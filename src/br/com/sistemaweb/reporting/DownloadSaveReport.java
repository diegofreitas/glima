/**
 * Este artefato de software foi desenvolvido por mim (root) em 21/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.reporting;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import br.com.sistemaweb.ApplicationContext;
import br.com.sistemaweb.utils.Download;

/**
 * @author root
 * 
 */
public class DownloadSaveReport implements ISaveReport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sistemaweb.reporting.ISaveReport#save()
	 */
	@Override
	public void save(Report report) {
		Download download = new Download();
		download.setOutputStream(report.getReportOutputStream());
		download.setContentType(report.getExportType().getContentType());
		download.setFileName(report.getReportName()+report.getExportType().getExtension());
		download.send();
	}

}
