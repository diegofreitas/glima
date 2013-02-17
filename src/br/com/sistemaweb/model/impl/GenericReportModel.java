/**
 * Este artefato de software foi desenvolvido por mim (Diego Lins de Freitas) em 18/01/2009
 * e eh minha propriedade intelectual, podendo ser utilizado se, e somente
 * se eu autorizar, senao vou ficar muito puto com voce.
 */
package br.com.sistemaweb.model.impl;

import java.util.List;

import javax.faces.event.ActionEvent;

import br.com.sistemaweb.MessageFactory;
import br.com.sistemaweb.model.IReportModel;
import br.com.sistemaweb.persistence.query.IQueryObject;
import br.com.sistemaweb.reporting.ContentType;
import br.com.sistemaweb.reporting.IReport;
import br.com.sistemaweb.reporting.Report;
import br.com.sistemaweb.reporting.HDSaveReport;
import br.com.sistemaweb.reporting.SessionSaveReport;
import br.com.sistemaweb.utils.Utils;

/**
 * @author Diego Lins de Freitas
 *
 */
public class GenericReportModel<T> implements IReportModel<T>{
	
	private MessageFactory messageFactory ;

	private Object specification;
	
	private List<T> list;
	
	private IReport<T> report =new Report<T>();
	private IQueryObject<T> query;
	
	
	public List<T> search() {
		query.setSpecification(specification);
		return query.list();
	}
	
	public void export(ContentType exportType,List<T> list) throws Exception{
		report.setSource(list);
		report.setExportType(exportType);
		report.export();
	}

	public IQueryObject<T> getQuery() {
		return query;
	}

	public void setQuery(IQueryObject<T> query) {
		this.query = query;
	}

	/* (non-Javadoc)
	 * @see br.com.sistemaweb.controller.IReportFacade#getReport()
	 */
	public IReport<T> getReport() {
		return report;
	}


	/* (non-Javadoc)
	 * @see br.com.sistemaweb.controller.IReportFacade#setReport(br.com.sistemaweb.reporting.IReport)
	 */

	public void setReport(IReport<T> report) {
		this.report=report;
	}

	
	public GenericReportModel(String reportName,IQueryObject<T> query) {
		report.setReportName(reportName);
		this.query=query;
	}

	public void search(ActionEvent e){
		setList(search());
		try {
			Report<T> report = new Report<T>();
			report.setReportName(getReport().getReportName());
			report.setSaveReport(new SessionSaveReport());
			report.setSource(list);
			report.setExportType(ContentType.PDF);
			report.export();
		} catch (Exception e1) {
			getMessageFactory().addErrorMessage("Ocorreu um erro inesperado" + e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	public MessageFactory getMessageFactory() {
		if(messageFactory== null)
			messageFactory=new MessageFactory();
		
		return messageFactory;
	}

	public void export(ActionEvent e) throws Exception{
		setList(search());
		ContentType exportType=(ContentType) Utils.getUIParameter("type", e);
		export(exportType,list);
	}

	public Object getSpecification() {
		return specification;
	}

	public void setSpecification(Object specification) {
		this.specification = specification;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
