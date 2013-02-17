/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 05/02/2009 18:05:52
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.reporting;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.jasper.JasperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Diego Lins de Freitas
 *
 */
public interface IReport<T> {
	
	public void setDefaulReportPath(String defaulReportPath) ;

	public String getDefaulReportPath() ;

	public String getReportName() ;

	public void setReportName(String reportName) ;

	public InputStream getReportStream() ;

	public void setReportStream(InputStream reportStream) ;

	public void setSource(Collection<T> source) ;

	public Collection<T> getSource() ;

	public void export() throws Exception ;

	public void setExportType(ContentType exportType) ;

	public ContentType getExportType() ;

	public void setReportOutputStream(ByteArrayOutputStream reportOutputStream) ;

	public ByteArrayOutputStream getReportOutputStream() ;

	public void setSaveReport(ISaveReport saveReport) ;

	public ISaveReport getSaveReport() ;

}


