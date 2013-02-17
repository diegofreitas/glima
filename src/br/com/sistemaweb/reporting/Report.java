/**
 * Este artefato de software foi desenvolvido por mim (root) em 19/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.reporting;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;

import org.apache.jasper.JasperException;

import br.com.sistemaweb.utils.Configuration;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.flex.JRSwfExporter;

/**
 * @author root
 * 
 */
public class Report<T> implements IReport<T>{

	private String reportName;
	private InputStream reportInputStream;
	private ByteArrayOutputStream reportOutputStream;//TODO mudarpara outputstream ou byte[]
	private String defaulReportPath = Configuration.valueFor("report.path");
	private Collection<T> source;
	private JasperPrint print;
	private JRDataSource dataSource;
	private ContentType exportType;
	private ISaveReport saveReport=new DownloadSaveReport();

	public void setDefaulReportPath(String defaulReportPath) {
		this.defaulReportPath = defaulReportPath;
	}

	public String getDefaulReportPath() {
		return defaulReportPath;
	}

	private void createReportStream() {
		reportInputStream = getClass().getResourceAsStream(
				defaulReportPath + reportName + ".jasper");
	}

	protected void createDataSource() throws Exception {
		dataSource = new JRBeanCollectionDataSource(source);
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public InputStream getReportStream() {
		return reportInputStream;
	}

	public void setReportStream(InputStream reportStream) {
		this.reportInputStream = reportStream;
	}

	public void createJasperPrint() {
		try {
			createReportStream();
			createDataSource();
			setPrint((JasperFillManager.fillReport(this.reportInputStream,
					null, this.dataSource)));
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSource(Collection<T> source) {
		this.source = source;
	}

	public Collection<T> getSource() {
		return source;
	}

	public void setPrint(JasperPrint print) {
		this.print = print;
	}

	public JasperPrint getPrint() {
		return print;
	}

	public void export() throws Exception {
		if(exportType==null){
			throw new JasperException("Export Type not defined");
		}
		createJasperPrint();
		if (exportType.equals(ContentType.PDF)) {
			doExport(new JRPdfExporter());
			return;
		}
		if (exportType.equals(ContentType.XLS)) {
			doExport(new JRXlsExporter());
			return;
		}
		if (exportType.equals(ContentType.RTF)) {
			doExport(new JRRtfExporter());
			return;
		}
		if (exportType.equals(ContentType.SWF)) {
			doExport(new JRSwfExporter());
			return;
		}
	}

	private void doExport(JRExporter exporter) throws Exception{
		reportOutputStream = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,reportOutputStream);
		exporter.exportReport();
		saveReport.save(this);
	}

	public void setExportType(ContentType exportType) {
		this.exportType = exportType;
	}

	public ContentType getExportType() {
		return exportType;
	}

	public void setReportOutputStream(ByteArrayOutputStream reportOutputStream) {
		this.reportOutputStream = reportOutputStream;
	}

	public ByteArrayOutputStream getReportOutputStream() {
		return reportOutputStream;
	}

	public void setSaveReport(ISaveReport saveReport) {
		this.saveReport = saveReport;
	}

	public ISaveReport getSaveReport() {
		return saveReport;
	}

}